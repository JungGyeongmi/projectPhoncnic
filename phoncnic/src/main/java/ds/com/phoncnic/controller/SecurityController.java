package ds.com.phoncnic.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class SecurityController {
    
    @GetMapping("/accessError")
    public String getAccessDeniedPage(Authentication auth) {
        log.info("auth........");
        log.info(auth.getPrincipal());
        if(auth.getPrincipal()==null) {
            return "redirect:/member/login";
        } else {
            log.info("access denied....");
            return "redirect:/denied";
        }
    }

    @GetMapping("/denied")
    public String getDeniedPage() {
        log.info("denied........");
        return "/accessError";
    }
}
