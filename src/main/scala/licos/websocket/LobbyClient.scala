package licos.websocket

import licos.json2protocol.lobby.Json2LobbyMessageProtocol
import licos.protocol.element.Protocol
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.engine.async.processing.lobby.LobbyProcessingEngineFactory
import licos.protocol.engine.async.processing.{LobbyPE, SpecificProcessingEngineFactory}
import licos.routine.protocol.async.lobby.{
  AuthorizationRequestAE,
  AuthorizationRequestAcceptedResponseAE,
  AvatarInfoAE,
  LobbyAE,
  LobbyBox,
  PingAE,
  PlayedAE,
  RobotPlayerSelectionPageAE,
  SearchResultAE,
  SettingsAE,
  WaitingPageAE
}
import play.api.Configuration
import play.api.libs.json.JsValue

import scala.concurrent.{ExecutionContext, Future}

class LobbyClient(config: Configuration) extends WebSocketRequest(config) {
  override protected lazy val endPointName: String = "lobby"

  protected val processingEngineFactory: LobbyProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(LobbyPE)
    .asInstanceOf[LobbyProcessingEngineFactory]
    .set(new AuthorizationRequestAcceptedResponseAE())
    .set(new AuthorizationRequestAE())
    .set(new AvatarInfoAE())
    .set(new LobbyAE())
    .set(new PingAE())
    .set(new PlayedAE())
    .set(new RobotPlayerSelectionPageAE)
    .set(new SearchResultAE())
    .set(new SettingsAE())
    .set(new WaitingPageAE())

  override protected def parse(json: JsValue)(implicit ec: ExecutionContext): Future[Protocol] = {
    Future
      .sequence(
        Json2LobbyMessageProtocol
          .toProtocolOpt(json)
          .map { protocol: LobbyMessageProtocol =>
            processingEngineFactory.create.process(
              new LobbyBox(),
              protocol
            )
          }
      )
      .filter(_.nonEmpty)
      .map(_.head)
  }

}
