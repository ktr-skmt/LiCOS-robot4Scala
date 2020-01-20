package licos.websocket

import licos.json2protocol.auth.Json2AuthMessageProtocol
import licos.protocol.element.Protocol
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.engine.async.processing.{AuthPE, SpecificProcessingEngineFactory}
import licos.protocol.engine.async.processing.auth.AuthProcessingEngineFactory
import licos.routine.protocol.async.auth.{AuthBox, AuthenticationRequestResponseAE, AuthorizationRequestResponseAE}
import play.api.Configuration
import play.api.libs.json.JsValue

import scala.concurrent.{ExecutionContext, Future}

class AuthClient(config: Configuration) extends WebSocketRequest(config) {
  override protected lazy val endPointName: String = "auth"

  protected val processingEngineFactory: AuthProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(AuthPE)
    .asInstanceOf[AuthProcessingEngineFactory]
    .set(new AuthenticationRequestResponseAE())
    .set(new AuthorizationRequestResponseAE())

  override protected def parse(json: JsValue)(implicit ec: ExecutionContext): Future[Protocol] = {
    Future
      .sequence(
        Json2AuthMessageProtocol
          .toProtocolOpt(json)
          .map { protocol: AuthMessageProtocol =>
            processingEngineFactory.create.process(
              new AuthBox(),
              protocol
            )
          }
      )
      .filter(_.nonEmpty)
      .map(_.head)
  }
}
