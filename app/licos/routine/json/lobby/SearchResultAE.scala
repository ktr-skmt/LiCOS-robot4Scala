package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonSearchResult
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.SearchResultAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class SearchResultAE extends SearchResultAnalysisEngine {
  override def process(box: BOX, searchResult: JsonSearchResult): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(searchResult))
      case _ => Left(Json.toJson(searchResult))
    }
  }
}
