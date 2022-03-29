package ds.com.phoncnic.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomAccessDeniedHandler implements
 AccessDeniedHandler, AuthenticationFailureHandler{
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
      AccessDeniedException accessDeniedException) throws IOException, ServletException {
    log.error("Access Denied Handler");
    log.error("Redirect............");
    log.error("request.getContextPath():"+request.getContextPath());
    response.sendRedirect(request.getContextPath()+"/accessError");
  }

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException exception) throws IOException, ServletException {
      log.error("Authentication Failure Handler");
      log.error("Redirect............");
      log.error("request.getContextPath():"+request.getContextPath());
      response.sendRedirect(request.getContextPath()+"/accessError");
    }
}
