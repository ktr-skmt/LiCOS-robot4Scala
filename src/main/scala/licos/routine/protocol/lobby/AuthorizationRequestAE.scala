package licos.routine.protocol.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AuthorizationRequestProtocol
import licos.protocol.engine.analysis.lobby.server2client.AuthorizationRequestAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.util.{Failure, Try}

final class AuthorizationRequestAE extends AuthorizationRequestAnalysisEngine {
  override def process(
      box:                          LobbyBOX,
      authorizationRequestProtocol: AuthorizationRequestProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
