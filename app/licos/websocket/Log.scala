package licos.websocket

import akka.actor.ActorSystem
import akka.event.{EventStream, Logging, LoggingAdapter}
import play.api.Logger

trait Log {
  private val logger = Logger(getClass)
  private def getLogging(eventStream: EventStream): LoggingAdapter = {
    Logging(eventStream, logger.underlyingLogger.getName)
  }
  protected def getLogging(actorSystem: ActorSystem): LoggingAdapter = {
    getLogging(actorSystem.eventStream)
  }
}
