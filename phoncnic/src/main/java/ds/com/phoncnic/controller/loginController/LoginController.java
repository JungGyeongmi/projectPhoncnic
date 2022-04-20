package ds.com.phoncnic.controller.loginController;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member")
public class LoginController {
    
    @GetMapping("/login")
    public String getLoginPage(
        HttpSession session,
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication auth) {
        log.info("request...");
        log.info(request.getHeader("Referer"));
        String referUrl = request.getHeader("Referer");
        if(auth != null && auth.getDetails() != null) {
            try {  response.sendRedirect(referUrl);
            } catch (IOException e) { e.printStackTrace(); }
        }

        session.setAttribute("referUrl", referUrl);
        request.setAttribute("referUrl", referUrl);

        log.info("login page......");
        return "/member/login";
    }
}
