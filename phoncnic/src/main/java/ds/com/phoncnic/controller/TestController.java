package ds.com.phoncnic.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class TestController {

    @GetMapping("/test")
    public String test () {
        log.info("test....");
        return "testhtml/test";
    }

    @GetMapping("/security")
    public String securityTest (@AuthenticationPrincipal AuthMemberDTO authMember, Model model) {
        log.info("test...."+authMember);
        model.addAttribute("session", authMember);
        return "testhtml/securitytest";
    }

}
