package ds.com.phoncnic.controller.dyningController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.service.dyning.DyningService;
import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/manage/dyning")
@RequiredArgsConstructor
public class DyningSettingController {
    private final DyningService dyningService;
    private final EmojiService emojiService;
    //가게 등록 폼
    @GetMapping("/register")
    public String dyningRegister() {
        return "/manage/dyning/register";
    }
    //가게 수정 폼
    @GetMapping("/modify")
    public String dyningModify() {
        return "/manage/dyning/modify";
    }
    //등록 가게 열람 폼
    @GetMapping("/read")
    public String dyningRead() {
        
        return "/manage/dyning/read";
    }
    // 가게를 처음으로 등록시
    @PostMapping("/register")
    public String register(DyningDTO dyningDTO, RedirectAttributes ra) {
        log.info("dyning register....."+dyningDTO);
        Long dno = dyningService.register(dyningDTO);
        ra.addFlashAttribute("dno",dno);
         
        // 가게를 등록하고 난 후 바로 아래로 이동
        return "redirect:/manage/dyning/read";
    }
    
}