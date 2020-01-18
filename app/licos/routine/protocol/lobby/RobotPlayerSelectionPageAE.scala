package licos.routine.protocol.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.RobotPlayerSelectionPageProtocol
import licos.protocol.engine.analysis.lobby.server2client.RobotPlayerSelectionPageAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.util.{Failure, Try}

final class RobotPlayerSelectionPageAE extends RobotPlayerSelectionPageAnalysisEngine {
  override def process(
      box:                              LobbyBOX,
      robotPlayerSelectionPageProtocol: RobotPlayerSelectionPageProtocol
  ): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
