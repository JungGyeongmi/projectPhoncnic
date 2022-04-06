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
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
  throws IOException, ServletException {

    AuthMemberDTO authMemberDTO = (AuthMemberDTO) auth.getPrincipal();

    log.warn("Login Success");

    List<String> roleNames = new ArrayList<>();

    authMemberDTO.getAuthorities().forEach(authority -> { roleNames.add(authority.getAuthority()); });

    log.warn("ROLE NAMES : " + roleNames);

    HttpSession session = request.getSession(false);
    if (session != null) {
      log.info( request.getPathInfo());
      String redirectUrl = (String) session.getAttribute("prevPage");
      log.info("redirect url "+redirectUrl);
      if (redirectUrl != null) {
        response.sendRedirect(redirectUrl);
        session.removeAttribute("prevPage");
      } else {
        log.info("boboobobobo");
        response.sendRedirect(request.getContextPath()+"/");
      }
    }
  }
}