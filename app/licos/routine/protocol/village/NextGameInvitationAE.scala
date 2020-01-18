package licos.routine.protocol.village

import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.server2client.NextGameInvitationProtocol
import licos.protocol.engine.analysis.village.server2client.NextGameInvitationAnalysisEngine
import licos.protocol.engine.processing.village.{VillageBOX, VillageBOXNotFoundException}

import scala.util.{Failure, Try}

final class NextGameInvitationAE extends NextGameInvitationAnalysisEngine {
  override def process(box: VillageBOX, nextGameInvitation: NextGameInvitationProtocol): Try[VillageMessageProtocol] = {
    box match {
      case _: VillageBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new VillageBOXNotFoundException())
    }
  }
}
