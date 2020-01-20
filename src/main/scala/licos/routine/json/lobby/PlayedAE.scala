package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonPlayed
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.PlayedAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class PlayedAE extends PlayedAnalysisEngine {
  override def process(box: BOX, played: JsonPlayed): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(played))
      case _ => Left(Json.toJson(played))
    }
  }
}
