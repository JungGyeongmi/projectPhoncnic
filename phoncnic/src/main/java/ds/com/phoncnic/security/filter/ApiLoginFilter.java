package ds.com.phoncnic.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.security.util.JWTUtil;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ApiLoginFilter
    extends AbstractAuthenticationProcessingFilter {

  private JWTUtil jwtUtil;

  public ApiLoginFilter(String defaultFilterProcessesUrl,
      JWTUtil jwtUtil) {
    super(defaultFilterProcessesUrl);
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException,
      IOException, ServletException {
    log.info("-----------------ApiLoginFilter------------------------");
    log.info("attemptAuthentication");
    String email = request.getParameter("email");
    String pw = request.getParameter("pw");
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, pw);

    return getAuthenticationManager().authenticate(authToken);
  }

  @Override
  protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response,
  FilterChain chain, Authentication authResult) throws IOException, ServletException {

    log.info("ApiLoginFilter successfulAuthentication:" + authResult);
    log.info("Principal:" + authResult.getPrincipal());

    String email = ((AuthMemberDTO) authResult.getPrincipal()).getId();
    String token = null;
    
    try {
      token = jwtUtil.generateToken(email);
      response.setContentType("text/plain");
      response.getOutputStream().write(token.getBytes());
      log.info(token);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
