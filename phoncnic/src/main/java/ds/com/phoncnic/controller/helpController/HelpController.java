package ds.com.phoncnic.controller.helpController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.HelpDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.service.help.HelpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/help")
@Log4j2
@RequiredArgsConstructor
public class HelpController {
    
    private final HelpService helpService;

    @GetMapping({"", "/", "/list"})
    public String getQnaHome(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", helpService.getQnaList(pageRequestDTO));
        return "/help/list";
    }

    @GetMapping("register")
    public String QnaRegister(){
        return "/help/register";
    }


    @PostMapping("/register")
    public String QnaRegisterPost(HelpDTO helpDTO, RedirectAttributes ra){
        log.info("Qna resister.................:"+helpDTO);

        //새로 추가된 Qna 번호
        Long qno = helpService.register(helpDTO);
      
        ra.addFlashAttribute("msg", qno);
        return "redirect:/help/list";
    }
}