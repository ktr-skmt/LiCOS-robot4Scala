package licos.routine.protocol.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.WaitingPageProtocol
import licos.protocol.engine.analysis.lobby.server2client.WaitingPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.util.{Failure, Try}

final class WaitingPageAE extends WaitingPageAnalysisEngine {
  override def process(box: LobbyBOX, waitingPageProtocol: WaitingPageProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
