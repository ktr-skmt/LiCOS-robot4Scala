package licos.api

import licos.protocol.engine.processing.auth.AuthProcessingEngineFactory
import licos.protocol.engine.processing.lobby.LobbyProcessingEngineFactory
import licos.protocol.engine.processing.village.VillageProcessingEngineFactory
import licos.protocol.engine.processing.{AuthPE, LobbyPE, SpecificProcessingEngineFactory, VillagePE}
import licos.routine.protocol.auth.{AuthenticationRequestResponseAE, AuthorizationRequestResponseAE}
import licos.routine.protocol.lobby._
import licos.routine.protocol.village.{NextGameInvitationAE, NextGameInvitationIsClosedAE}

final case class ProtocolAPI() extends API {
  val auth: AuthProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(AuthPE)
    .asInstanceOf[AuthProcessingEngineFactory]
    .set(new AuthenticationRequestResponseAE())
    .set(new AuthorizationRequestResponseAE())
  val lobby: LobbyProcessingEngineFactory = SpecificProcessingEngineFactory
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
  val village: VillageProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(VillagePE)
    .asInstanceOf[VillageProcessingEngineFactory]
    .set(new NextGameInvitationAE())
    .set(new NextGameInvitationIsClosedAE)
}
