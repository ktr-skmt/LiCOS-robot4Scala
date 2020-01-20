package licos.routine.protocol.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol
import licos.protocol.engine.analysis.lobby.server2client.AvatarInfoAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.util.{Failure, Try}

final class AvatarInfoAE extends AvatarInfoAnalysisEngine {
  override def process(box: LobbyBOX, avatarInfoProtocol: AvatarInfoProtocol): Try[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new LobbyBOXNotFoundException())
    }
  }
}
