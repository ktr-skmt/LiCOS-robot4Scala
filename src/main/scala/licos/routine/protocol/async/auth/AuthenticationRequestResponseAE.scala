package licos.routine.protocol.async.auth

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthenticationRequestResponseProtocol
import licos.protocol.engine.async.analysis.auth.server2robot.AuthenticationRequestResponseAnalysisEngine
import licos.protocol.engine.processing.auth.{AuthBOX, AuthBOXNotFoundException}

import scala.concurrent.{ExecutionContext, Future}

final class AuthenticationRequestResponseAE extends AuthenticationRequestResponseAnalysisEngine {
  override def process(box: AuthBOX, authenticationRequestResponseProtocol: AuthenticationRequestResponseProtocol)(
      implicit ec:          ExecutionContext
  ): Future[AuthMessageProtocol] = {
    box match {
      case _: AuthBox =>
        //TODO
        Future.failed(new NotImplementedError())
      case _ => Future.failed(new AuthBOXNotFoundException())
    }
  }
}
