package ds.com.phoncnic.controller.dyningRestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/dyning")
public class DyningSearchRestController {

    // @GetMapping("/restaurant/search")
    // public void search1(String keyword, @ModelAttribute("pageRequestDTO")
    // PageRequestDTO pageRequestDTO, Model model) {
    // // log.info("dno: ");
    // }

    @GetMapping("/restaurant/search")
    public void search1(PageRequestDTO pageRequestDTO, Model model) {

    }

}
