package licos.routine.json.village

import licos.json.element.village.invite.JsonNextGameInvitation
import licos.json.engine.BOX
import licos.json.engine.analysis.village.server2client.NextGameInvitationAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class NextGameInvitationAE extends NextGameInvitationAnalysisEngine {
  override def process(box: BOX, nextGameInvitation: JsonNextGameInvitation): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox =>
        //TODO
        Right(Json.toJson(nextGameInvitation))
      case _ => Left(Json.toJson(nextGameInvitation))
    }
  }
}
