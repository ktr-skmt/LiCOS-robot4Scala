package licos.routine.protocol.async.auth

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.server2robot.AuthorizationRequestResponseProtocol
import licos.protocol.engine.async.analysis.auth.server2robot.AuthorizationRequestResponseAnalysisEngine
import licos.protocol.engine.processing.auth.{AuthBOX, AuthBOXNotFoundException}

import scala.concurrent.{ExecutionContext, Future}

final class AuthorizationRequestResponseAE extends AuthorizationRequestResponseAnalysisEngine {
  override def process(box: AuthBOX, authorizationRequestResponseProtocol: AuthorizationRequestResponseProtocol)(
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
