package ds.com.phoncnic.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
  @Override
  public void onLogoutSuccess(HttpServletRequest request, 
  HttpServletResponse response, Authentication auth)
      throws IOException, ServletException {

    log.warn("logoutsuccess...");
    log.info("auth.getDetails : "+auth.getDetails());

    if(auth != null && auth.getDetails() != null){
      try {
        request.getSession().invalidate();
        log.info("logout invalidate....");
      } catch (Exception e) {e.printStackTrace();}

      response.setStatus(HttpServletResponse.SC_OK);
      response.sendRedirect(request.getContextPath()+"/");
    }
  }
}
