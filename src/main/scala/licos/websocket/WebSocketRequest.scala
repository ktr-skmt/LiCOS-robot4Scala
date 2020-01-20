package licos.websocket

import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ws.{Message, TextMessage, WebSocketRequest, WebSocketUpgradeResponse}
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.stream.scaladsl.{Flow, Keep, Sink, Source, SourceQueueWithComplete}
import licos.protocol.element.Protocol
import play.api.Configuration
import play.api.libs.json.{JsValue, Json}

import scala.concurrent.{ExecutionContext, Future}

abstract class WebSocketRequest(config: Configuration) {

  protected val endPointName: String
  private val url:        String = s"wss://licos.online/${endPointName}Message"
  private val bufferSize: Int    = config.get[Int]("werewolf.webSocket.bufferSize")
  //private val parallelism: Int = config.get[Int]("werewolf.webSocket.parallelism")
  private implicit val system:       ActorSystem       = ActorSystem()
  private implicit val materializer: ActorMaterializer = ActorMaterializer()
  import system.dispatcher
  private val webSocketFlow: Flow[Message, JsValue, Future[WebSocketUpgradeResponse]] = {
    Http().webSocketClientFlow(WebSocketRequest(url)).collect {
      case TextMessage.Strict(message: String) => Json.parse(message)
    }
  }
  private def run[M1, M2](
      input:  Source[String, M1],
      output: Sink[JsValue, M2]
  ): ((M1, Future[WebSocketUpgradeResponse]), M2) = {
    input
      .map(direction => TextMessage(direction))
      .viaMat(webSocketFlow)(Keep.both)
      .toMat(output)(Keep.both)
      .run()
  }
  private val clientSource: Source[String, SourceQueueWithComplete[String]] = {
    Source.queue[String](
      bufferSize,
      OverflowStrategy.backpressure
    )
  }
  protected def parse(json: JsValue)(implicit ec: ExecutionContext): Future[Protocol]
  private def sink: Sink[JsValue, Future[Done]] = Sink.foreach[JsValue] { json: JsValue =>
    parse(json).foreach { protocol: Protocol =>
      send(protocol)
    }
  }
  private val (
    (
      inputMat: SourceQueueWithComplete[String],
      result:   Future[WebSocketUpgradeResponse]
    ),
    _: Future[Done]
  ) = run(clientSource, sink)

  result.recover {
    case e: Throwable =>
      println(s"Failed to connect to server $e")
      System.exit(0)
  }

  def send(msg: JsValue): Unit = {
    inputMat.offer(Json.stringify(msg))
  }

  def send(msg: Protocol): Unit = {
    msg.toJsonOpt.foreach { jsValue: JsValue =>
      send(jsValue)
    }
  }

}
