package ds.com.phoncnic.controller.loginController;

import javax.servlet.http.HttpServletRequest;

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
    public String getLoginPage(HttpServletRequest request, Authentication auth) {
        log.info("request...");
        log.info(request.getServletPath());
        if(auth!=null && auth.getDetails() != null) {
            return "redirect:/";
        }
        log.info("login page......");
        return "/member/login";
    }

}
