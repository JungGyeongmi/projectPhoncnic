package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class SecurityController {
    
    @GetMapping("/accessError")
    public void getAccessDeniedPage() {
        log.info("access denied...."); 
    }
}
