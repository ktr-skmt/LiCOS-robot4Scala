package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonAuthorizationRequest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.AuthorizationRequestAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class AuthorizationRequestAE extends AuthorizationRequestAnalysisEngine {
  override def process(box: BOX, authorizationRequest: JsonAuthorizationRequest): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(authorizationRequest))
      case _ => Left(Json.toJson(authorizationRequest))
    }
  }
}
