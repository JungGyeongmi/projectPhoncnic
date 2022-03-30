package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ds.com.phoncnic.service.mypage.MemberService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class TestController {

    // private final MemberService memberService;

    @GetMapping("/test")
    public String test () {
        return "test";
    }

    @GetMapping("/test2")
    public String test2 () {
        
        return "test2";
    }

}
