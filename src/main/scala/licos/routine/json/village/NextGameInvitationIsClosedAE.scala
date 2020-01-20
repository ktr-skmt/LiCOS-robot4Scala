package licos.routine.json.village

import licos.json.element.village.invite.JsonNextGameInvitationIsClosed
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.NextGameInvitationIsClosedAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class NextGameInvitationIsClosedAE extends NextGameInvitationIsClosedAnalysisEngine {
  override def process(
      box:                        BOX,
      nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed
  ): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox =>
        //TODO
        Right(Json.toJson(nextGameInvitationIsClosed))
      case _ => Left(Json.toJson(nextGameInvitationIsClosed))
    }
  }
}
