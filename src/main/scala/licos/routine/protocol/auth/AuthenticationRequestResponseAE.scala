package licos.routine.protocol.auth

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthenticationRequestResponseProtocol
import licos.protocol.engine.analysis.auth.server2robot.AuthenticationRequestResponseAnalysisEngine
import licos.protocol.engine.processing.auth.{AuthBOX, AuthBOXNotFoundException}

import scala.util.{Failure, Try}

final class AuthenticationRequestResponseAE extends AuthenticationRequestResponseAnalysisEngine {
  override def process(
      box:                                   AuthBOX,
      authenticationRequestResponseProtocol: AuthenticationRequestResponseProtocol
  ): Try[AuthMessageProtocol] = {
    box match {
      case _: AuthBox =>
        //TODO
        Failure(new NotImplementedError())
      case _ => Failure(new AuthBOXNotFoundException())
    }
  }
}
