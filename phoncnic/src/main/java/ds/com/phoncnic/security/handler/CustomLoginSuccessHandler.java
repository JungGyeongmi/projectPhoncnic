package ds.com.phoncnic.security.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
  private RedirectStrategy redirect = new DefaultRedirectStrategy();
  private PasswordEncoder passwordEncoder;
  public CustomLoginSuccessHandler(PasswordEncoder passwordEncoder){
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication auth)
      throws IOException, ServletException {
    log.warn("Login Success");
    AuthMemberDTO authMemberDTO = (AuthMemberDTO) auth.getPrincipal();
    boolean passwordResult = passwordEncoder.matches("1", authMemberDTO.getPassword());
    log.info("DTO.getPassword : "+authMemberDTO.getPassword());
    
    List<String> roleNames = new ArrayList<>();

    authMemberDTO.getAuthorities().forEach(authority ->{
      roleNames.add(authority.getAuthority());
    });

    log.warn("ROLE NAMES : "+roleNames);
    // if(roleNames.contains("ROLE_ADMIN")){
    //   response.sendRedirect(request.getContextPath()+"/sample/admin");
    //   return;
    // }
    // if(roleNames.contains("ROLE_MEMBER")){
    //   response.sendRedirect(request.getContextPath()+"/sample/member");
    //   return;
    // }
    // if(roleNames.contains("ROLE_USER")){
    //   response.sendRedirect(request.getContextPath()+"/sample/all");
    //   return;
    // }
    response.sendRedirect(request.getContextPath()+"/");
  }

}