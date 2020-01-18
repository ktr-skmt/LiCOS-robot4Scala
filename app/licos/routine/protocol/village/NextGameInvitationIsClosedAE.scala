package licos.routine.protocol.village

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationIsClosedProtocol
import licos.protocol.engine.analysis.village.server2client.NextGameInvitationIsClosedAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}

import scala.util.{Failure, Try}

final class NextGameInvitationIsClosedAE extends NextGameInvitationIsClosedAnalysisEngine {
  override def process(
      box:                        VillageBOX,
      nextGameInvitationIsClosed: NextGameInvitationIsClosedProtocol
  ): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBOX =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
