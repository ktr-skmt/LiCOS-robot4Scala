package licos.routine.protocol.async.village

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationProtocol
import licos.protocol.engine.async.analysis.village.server2client.NextGameInvitationAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}

import scala.concurrent.{ExecutionContext, Future}

final class NextGameInvitationAE extends NextGameInvitationAnalysisEngine {
  override def process(box: VillageBOX, nextGameInvitation: NextGameInvitationProtocol)(
      implicit ec:          ExecutionContext
  ): Future[VillageMessageProtocol] = {
    box match {
      case _: VillageBox =>
        //TODO
        Future.failed(new NotImplementedError())
      case _ => Future.failed(new VillageBOXNotFoundException())
    }
  }
}
