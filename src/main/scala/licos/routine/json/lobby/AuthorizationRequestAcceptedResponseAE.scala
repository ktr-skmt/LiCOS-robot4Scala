package licos.routine.json.lobby

import licos.json.element.lobby.server2client.JsonAuthorizationRequestAcceptedResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.AuthorizationRequestAcceptedResponseAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class AuthorizationRequestAcceptedResponseAE extends AuthorizationRequestAcceptedResponseAnalysisEngine {
  override def process(
      box:                                  BOX,
      authorizationRequestAcceptedResponse: JsonAuthorizationRequestAcceptedResponse
  ): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox =>
        //TODO
        Right(Json.toJson(authorizationRequestAcceptedResponse))
      case _ => Left(Json.toJson(authorizationRequestAcceptedResponse))
    }
  }
}
