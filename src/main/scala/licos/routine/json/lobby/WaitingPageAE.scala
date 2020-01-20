package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonWaitingPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.WaitingPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class WaitingPageAE extends WaitingPageAnalysisEngine {
  override def process(box: BOX, waitingPage: JsonWaitingPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(waitingPage))
      case _ => Left(Json.toJson(waitingPage))
    }
  }
}
