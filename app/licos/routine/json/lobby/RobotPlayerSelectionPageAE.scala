package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonRobotPlayerSelectionPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.RobotPlayerSelectionPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class RobotPlayerSelectionPageAE extends RobotPlayerSelectionPageAnalysisEngine {
  override def process(box: BOX, robotPlayerSelectionPage: JsonRobotPlayerSelectionPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(robotPlayerSelectionPage))
      case _ => Left(Json.toJson(robotPlayerSelectionPage))
    }
  }
}
