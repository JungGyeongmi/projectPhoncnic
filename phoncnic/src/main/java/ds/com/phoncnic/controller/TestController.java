package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.qna.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class TestController {
    
    private final EmojiService emojiService;

    private final QnaService qnaService;

    @GetMapping("/test")
    public String test (PageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("result", qnaService.getQnaList(pageRequestDTO));
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
