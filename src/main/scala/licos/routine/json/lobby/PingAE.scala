package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonPing
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.PingAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class PingAE extends PingAnalysisEngine {
  override def process(box: BOX, ping: JsonPing): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(ping))
      case _ => Left(Json.toJson(ping))
    }
  }
}
