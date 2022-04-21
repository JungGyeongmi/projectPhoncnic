package ds.com.phoncnic.security.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication auth)
      throws IOException, ServletException {
    HttpSession session = request.getSession(false);
    log.info("Success......");

    AuthMemberDTO authMemberDTO = (AuthMemberDTO) auth.getPrincipal();
    log.warn("Login Success");

    List<String> roleNames = new ArrayList<>();

    authMemberDTO.getAuthorities().forEach(authority -> {
      roleNames.add(authority.getAuthority());
    });

    log.warn("get  session referUrl" + session.getAttribute("referUrl"));

    int interval = session.getMaxInactiveInterval();
    log.info("session interval...." + interval);
    session.setMaxInactiveInterval(3600);
    interval = session.getMaxInactiveInterval();
    log.info("changed session interval " + interval);

    log.info("header cookie");
    log.warn(request.getHeaders("Host"));

    log.info("context path");
    log.warn(request.getContextPath());

    // String redirectUrl = session.getAttribute("referUrl").toString();

    // log.info(redirectUrl);

    if (session != null) {
      // if (redirectUrl != null) {
      // response.sendRedirect(redirectUrl);
      // session.removeAttribute("referUrl");
      // } else {
      log.info("--session null -- redirect null ---");
      response.sendRedirect(request.getContextPath() + "/");
      // }
    }
  }
}