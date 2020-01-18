package licos.routine.protocol.async.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PlayedProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.PlayedAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.concurrent.{ExecutionContext, Future}

final class PlayedAE extends PlayedAnalysisEngine {
  override def process(box: LobbyBOX, playedProtocol: PlayedProtocol)(
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
