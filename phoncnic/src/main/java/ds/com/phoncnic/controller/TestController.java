package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test () {
        log.info("test....");
        return "test";
    }

}
