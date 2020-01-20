package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonLobby
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.LobbyAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class LobbyAE extends LobbyAnalysisEngine {
  override def process(box: BOX, lobby: JsonLobby): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(lobby))
      case _ => Left(Json.toJson(lobby))
    }
  }
}
