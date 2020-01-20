package licos.websocket

import licos.entity.VillageInfoFromLobby
import licos.json2protocol.village.Json2VillageMessageProtocol
import licos.protocol.element.Protocol
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.async.processing.village.VillageProcessingEngineFactory
import licos.protocol.engine.async.processing.{SpecificProcessingEngineFactory, VillagePE}
import licos.routine.protocol.async.village.{NextGameInvitationAE, NextGameInvitationIsClosedAE, VillageBox}
import play.api.Configuration
import play.api.libs.json.JsValue

import scala.concurrent.{ExecutionContext, Future}

class VillageClient(config: Configuration, villageInfoFromLobby: VillageInfoFromLobby)
    extends WebSocketRequest(config) {
  override protected lazy val endPointName: String = "village"

  protected val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(VillagePE)
    .asInstanceOf[VillageProcessingEngineFactory]
    .set(new NextGameInvitationAE())
    .set(new NextGameInvitationIsClosedAE)

  override protected def parse(json: JsValue)(implicit ec: ExecutionContext): Future[Protocol] = {
    Future
      .sequence(
        Json2VillageMessageProtocol
          .toProtocolOpt(json, villageInfoFromLobby)
          .map { protocol: VillageMessageProtocol =>
            processingEngineFactory.create.process(
              new VillageBox(villageInfoFromLobby),
              protocol
            )
          }
      )
      .filter(_.nonEmpty)
      .map(_.head)
  }
}
