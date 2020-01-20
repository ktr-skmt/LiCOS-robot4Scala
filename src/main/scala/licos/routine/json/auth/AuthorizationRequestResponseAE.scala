package licos.routine.json.auth

import licos.json.element.auth.server2robot.JsonAuthorizationRequestResponse
import licos.json.engine.BOX
import licos.json.engine.analysis.auth.server2robot.AuthorizationRequestResponseAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class AuthorizationRequestResponseAE extends AuthorizationRequestResponseAnalysisEngine {
  override def process(
      box:                          BOX,
      authorizationRequestResponse: JsonAuthorizationRequestResponse
  ): Either[JsValue, JsValue] = {
    box match {
      case _: AuthBox =>
        //TODO
        Right(Json.toJson(authorizationRequestResponse))
      case _ => Left(Json.toJson(authorizationRequestResponse))
    }
  }
}
