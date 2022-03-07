package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ds.com.phoncnic.service.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class TestController {
    
    private final EmojiService emojiService;

    @GetMapping("/test")
    public String test () {
        return "test";
    }
    
    @GetMapping("/test/{id}")
    public String testGet (Model model,@PathVariable("id") String id) {
        model.addAttribute("emojiDTOList", emojiService.getEmojiList(id));
        log.info("get request user id...."+id);
        return "test";
    }

    @PostMapping("/test")
    public String testPost (Model model, @RequestParam(name ="id") String id){
        model.addAttribute("id", id);
        log.info("post request user id..."+id);
        return "redirect:/test/"+id;
    }
}
