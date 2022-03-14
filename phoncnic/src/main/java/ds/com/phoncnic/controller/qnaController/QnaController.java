package ds.com.phoncnic.controller.qnaController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.QnaDTO;
import ds.com.phoncnic.service.qna.QnaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/help")
@Log4j2
@RequiredArgsConstructor
public class QnaController {
    
    private final QnaService qnaService;

    @GetMapping({"", "/", "/list"})
    public String getQnaHome(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", qnaService.getQnaList(pageRequestDTO));
        return "/help/list";
    }

    @GetMapping("register")
    public String QnaRegister(){
        return "/help/register";
    }


    @PostMapping("/register")
    public String QnaRegisterPost(QnaDTO qnadto, RedirectAttributes ra){
        log.info("Qna resister.................:"+qnadto);

        //새로 추가된 Qna 번호
        Long qno = qnaService.register(qnadto);
      
        ra.addFlashAttribute("msg", qno);
        return "redirect:/help/list";
    }
}