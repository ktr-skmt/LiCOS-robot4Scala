package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonSettings
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.SettingsAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class SettingsAE extends SettingsAnalysisEngine {
  override def process(box: BOX, settings: JsonSettings): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(settings))
      case _ => Left(Json.toJson(settings))
    }
  }
}
