package licos.routine.protocol.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.SettingsProtocol
import licos.protocol.engine.analysis.lobby.server2client.SettingsAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.util.{Failure, Try}

final class SettingsAE extends SettingsAnalysisEngine {
  override def process(box: LobbyBOX, settingsProtocol: SettingsProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
