package licos.routine.protocol.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.LobbyProtocol
import licos.protocol.engine.analysis.lobby.server2client.LobbyAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.util.{Failure, Try}

final class LobbyAE extends LobbyAnalysisEngine {
  override def process(box: LobbyBOX, lobbyProtocol: LobbyProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
