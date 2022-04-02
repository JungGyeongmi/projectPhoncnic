package ds.com.phoncnic.controller.loginController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class LoginController {
    
    @GetMapping("/member/login")
    public void getLoginPage() {
        log.info("login page......");
    }
}
