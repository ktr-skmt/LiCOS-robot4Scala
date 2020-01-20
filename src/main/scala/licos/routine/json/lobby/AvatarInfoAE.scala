package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonAvatarInfo
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.AvatarInfoAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class AvatarInfoAE extends AvatarInfoAnalysisEngine {
  override def process(box: BOX, avatarInfo: JsonAvatarInfo): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(avatarInfo))
      case _ => Left(Json.toJson(avatarInfo))
    }
  }
}
