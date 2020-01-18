package licos.routine.protocol.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestAcceptedResponseProtocol
import licos.protocol.engine.analysis.lobby.server2client.AuthorizationRequestAcceptedResponseAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.util.{Failure, Try}

final class AuthorizationRequestAcceptedResponseAE extends AuthorizationRequestAcceptedResponseAnalysisEngine {
  override def process(
      box:                                          LobbyBOX,
      authorizationRequestAcceptedResponseProtocol: AuthorizationRequestAcceptedResponseProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
