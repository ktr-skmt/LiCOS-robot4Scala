package licos.routine.protocol.auth

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthorizationRequestResponseProtocol
import licos.protocol.engine.analysis.auth.server2robot.AuthorizationRequestResponseAnalysisEngine
import licos.protocol.engine.processing.auth.{AuthBOX, AuthBOXNotFoundException}

import scala.util.{Failure, Try}

final class AuthorizationRequestResponseAE extends AuthorizationRequestResponseAnalysisEngine {
  override def process(
      box:                                  AuthBOX,
      authorizationRequestResponseProtocol: AuthorizationRequestResponseProtocol
  ): Try[AuthMessageProtocol] = {
    box match {
      case _: AuthBOX =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new AuthBOXNotFoundException())
    }
  }
}
