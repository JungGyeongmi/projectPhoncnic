package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.service.mypage.CharacterLookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class TestController {
    private final CharacterLookService characterLookService;

    // private final MemberService memberService;

    @GetMapping("/test")
    public String test () {
        return "test";
    }

    @GetMapping("/test2")
    public String test2 (String id, Model model) {
        log.info("id:" + id);
        model.addAttribute("id",id);
        model.addAttribute("hairDTO", characterLookService.getCharacterHair(id));
        model.addAttribute("clothesDTO", characterLookService.getCharacterClothes(id));
        return "/test2";
    }

  
}

 
