package ds.com.phoncnic.controller.loginController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/member")
public class LoginController {
    
    @GetMapping("/login")
    public void getLoginPage() {
        log.info("login page......");
    }

    @GetMapping("/loginRequest")
    public void getLoginRequestPage() {
        log.info("login request......");
    }
}
