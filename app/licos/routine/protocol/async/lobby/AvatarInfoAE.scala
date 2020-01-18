package licos.routine.protocol.async.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.AvatarInfoAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.concurrent.{ExecutionContext, Future}

final class AvatarInfoAE extends AvatarInfoAnalysisEngine {
  override def process(box: LobbyBOX, avatarInfoProtocol: AvatarInfoProtocol)(
      implicit ec:          ExecutionContext
  ): Future[LobbyMessageProtocol] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Future.failed(new NotImplementedError())
      case _ => Future.failed(new LobbyBOXNotFoundException())
    }
  }
}
