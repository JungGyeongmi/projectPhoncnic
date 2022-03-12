package ds.com.phoncnic.controller.qnaController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.service.qna.QnaService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/qna")
@RequiredArgsConstructor
public class QnaController {
    
    private final QnaService qnaService;

    @GetMapping({"/", "/list"})
    public String getQnaHome(PageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("result", qnaService.getQnaList(pageRequestDTO));
        return "/qna/list";
    }

}
