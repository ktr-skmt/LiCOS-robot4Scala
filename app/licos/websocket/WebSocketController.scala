package licos.websocket

import akka.NotUsed
import akka.actor.ActorSystem
import akka.event.LoggingAdapter
import akka.stream.{Materializer, OverflowStrategy}
import akka.stream.scaladsl.{BroadcastHub, Flow, Keep, MergeHub, RunnableGraph, Sink, Source, SourceQueueWithComplete}
import licos.protocol.element.Protocol
import play.api.Configuration
import play.api.libs.json.JsValue
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

abstract class WebSocketController(config: Configuration, cc: ControllerComponents)(
    implicit actorSystem:                  ActorSystem,
    mat:                                   Materializer,
    ec:                                    ExecutionContext
) extends AbstractController(cc)
    with Log {
  protected implicit val log: LoggingAdapter = getLogging(actorSystem)
  private val parallelism:    Int            = 2

  private val (jsonSink, jsonSource): (Sink[JsValue, NotUsed], Source[JsValue, NotUsed]) = {
    val source: Source[JsValue, Sink[JsValue, NotUsed]] = MergeHub
      .source[JsValue]
      .log("source")
      .recoverWithRetries(
        -1, { case _: Exception => Source.empty }
      )
    val sink: Sink[JsValue, Source[JsValue, NotUsed]] = BroadcastHub.sink[JsValue]
    source
      .toMat(sink)(Keep.both)
      .run()
  }

  private val serverSource: Source[JsValue, SourceQueueWithComplete[JsValue]] = {
    Source
      .queue[JsValue](
        config.get[Int]("werewolf.webSocket.bufferSize"),
        OverflowStrategy.backpressure
      )
  }

  private val userFlow: Flow[JsValue, JsValue, NotUsed]                 = Flow.fromSinkAndSource(jsonSink, jsonSource)
  private val graph:    RunnableGraph[SourceQueueWithComplete[JsValue]] = serverSource.to(jsonSink)
  private val queue:    SourceQueueWithComplete[JsValue]                = graph.run()

  def send(msg: Protocol): Unit = {
    log.debug("WebSocketController.send")
    msg.toJsonOpt.foreach { jsValue: JsValue =>
      queue.offer(jsValue)
    }
  }

  protected def receive(msg: JsValue): Unit

  protected def parse(msg: JsValue, rh: RequestHeader): Future[Protocol]

  protected def socket: WebSocket = {
    log.debug("WebSocketController.messageSocket")

    def forbidden: Future[Either[Result, Flow[JsValue, JsValue, NotUsed]]] = {
      Future
        .successful(Left(Forbidden("forbidden")))
    }

    WebSocket.acceptOrResult[JsValue, JsValue] {
      case rh: RequestHeader if sameOriginCheck(rh) =>
        Future.successful {
          userFlow
            .mapAsync(parallelism) { msg: JsValue =>
              receive(msg)
              parse(msg, rh)
                .map(_.toJsonOpt)
                .filter(_.nonEmpty)
                .map(_.get)
            }
        }.map { flow: Flow[JsValue, JsValue, NotUsed] =>
          Right(flow): Either[Result, Flow[JsValue, JsValue, NotUsed]]
        }.recover {
          case err: Exception =>
            Left(InternalServerError(err.getMessage)): Either[Result, Flow[JsValue, JsValue, NotUsed]]
        }
      case rejected: RequestHeader =>
        log.error(s"Request $rejected failed same origin check")
        forbidden
    }
  }

  /**
    * Checks that the WebSocket comes from the same origin.  This is necessary to protect
    * against Cross-Site WebSocket Hijacking as WebSocket does not implement Same Origin Policy.
    *
    * See https://tools.ietf.org/html/rfc6455#section-1.3 and
    * http://blog.dewhurstsecurity.com/2013/08/30/security-testing-html5-websockets.html
    */
  private def sameOriginCheck(implicit rh: RequestHeader): Boolean = {
    // The Origin header is the domain the request originates from.
    // https://tools.ietf.org/html/rfc6454#section-7
    log.debug("Checking the ORIGIN")

    /**
      * Returns true if the value of the Origin header contains an acceptable value.
      */
    def originMatches(origin: String): Boolean = {
      config
        .get[Seq[String]]("play.filters.hosts.allowed")
        .contains(origin)
    }

    val port:     String = rh.host.split(':').last
    val mainPort: String = config.get[String]("mainPort")

    if (port == mainPort) {
      rh.headers.get("Origin") match {
        case Some(originValue: String) if originMatches(originValue) =>
          log.debug(s"originCheck: originValue = $originValue")
          true
        case Some(badOrigin: String) =>
          log.error(s"originCheck: rejecting request because Origin header value $badOrigin is not in the same origin")
          false
        case None =>
          log.error("originCheck: rejecting request because no Origin header found")
          false
      }
    } else {
      log.error(s"The port should not be $port but $mainPort.")
      false
    }
  }

}
