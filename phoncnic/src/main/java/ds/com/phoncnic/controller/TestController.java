package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class TestController {
    
    @GetMapping("/test")
    public String test () {
        log.info("test....");
        return "test";
    }
}
