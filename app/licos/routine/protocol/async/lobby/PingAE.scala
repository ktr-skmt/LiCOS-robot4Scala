package licos.routine.protocol.async.lobby

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.PingProtocol
import licos.protocol.engine.async.analysis.lobby.server2client.PingAnalysisEngine
import licos.protocol.engine.processing.lobby.{LobbyBOX, LobbyBOXNotFoundException}

import scala.concurrent.{ExecutionContext, Future}

final class PingAE extends PingAnalysisEngine {
  override def process(box: LobbyBOX, pingProtocol: PingProtocol)(
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
