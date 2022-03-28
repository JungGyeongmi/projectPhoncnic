package ds.com.phoncnic.controller.helpController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.HelpDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.service.help.HelpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/help")
@Log4j2
@RequiredArgsConstructor
public class HelpController {

    private final HelpService helpService;

    @GetMapping({ "", "/","/list" })
    public String getHelpHome(PageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("result", helpService.getQnaList(pageRequestDTO));
        return "/help/list";
    }


    @GetMapping("register")

    public String Register() {

        return "/help/register";
    }

    @PostMapping("/register")
    public String RegisterPost(HelpDTO helpDTO, RedirectAttributes ra) {
        log.info("help resister.................:" + helpDTO);

        // 새로 추가된 Qna 번호
        Long qno = helpService.register(helpDTO);

        ra.addFlashAttribute("msg", qno);
        return "redirect:/help/list";
    }

    @GetMapping({ "/read", "/modify" })
    public void read(long qno, Model model, PageRequestDTO pageRequestDTO) {
        log.info("read qno............:" + qno);

        HelpDTO helpDTO = helpService.get(qno);
        model.addAttribute("dto", helpDTO);
    }

    @PostMapping("/modify")
    public String modify(HelpDTO helpDTO, RedirectAttributes ra, Long qno, PageRequestDTO pageRequestDTO) {
        log.info("modify..........qno:" + helpDTO);

        helpService.modify(helpDTO);

        ra.addAttribute("page", pageRequestDTO.getPage());
        ra.addAttribute("type", pageRequestDTO.getType());
        ra.addAttribute("keyword", pageRequestDTO.getKeyword());
        ra.addAttribute("qno", helpDTO.getQno());

        return "redirect:/help/read";
    }

    @PostMapping("/remove")
    public String remove(Long qno, RedirectAttributes ra, PageRequestDTO pageRequestDTO) {
        log.info("remove...........qno:" + qno);

        helpService.remove(qno);

        if (helpService.getQnaList(pageRequestDTO).getDtoList().size() == 0 && pageRequestDTO.getPage() != 1) {
            pageRequestDTO.setPage(pageRequestDTO.getPage() - 1);
        };
        ra.addFlashAttribute("msg2", qno);
        ra.addFlashAttribute("page", pageRequestDTO.getPage());
        ra.addFlashAttribute("type", pageRequestDTO.getType());
        ra.addFlashAttribute("keyword", pageRequestDTO.getKeyword());

        return "redirect:/help/list";

    }
}