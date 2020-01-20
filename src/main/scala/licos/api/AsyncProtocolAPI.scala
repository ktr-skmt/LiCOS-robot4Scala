package licos.api

import licos.protocol.engine.async.processing.auth.AuthProcessingEngineFactory
import licos.protocol.engine.async.processing.lobby.LobbyProcessingEngineFactory
import licos.protocol.engine.async.processing.village.VillageProcessingEngineFactory
import licos.protocol.engine.async.processing.{AuthPE, LobbyPE, SpecificProcessingEngineFactory, VillagePE}
import licos.routine.protocol.async.auth.{AuthenticationRequestResponseAE, AuthorizationRequestResponseAE}
import licos.routine.protocol.async.lobby._
import licos.routine.protocol.async.village.{NextGameInvitationAE, NextGameInvitationIsClosedAE}

final case class AsyncProtocolAPI() extends API {
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
