package licos.api

import licos.json.engine.processing._
import licos.routine.json.auth.{AuthenticationRequestResponseAE, AuthorizationRequestResponseAE}
import licos.routine.json.lobby._
import licos.routine.json.village.{NextGameInvitationAE, NextGameInvitationIsClosedAE}

final case class JsonAPI() extends API {
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
