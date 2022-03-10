package ds.com.phoncnic.controller.dyningController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.service.dyning.DyningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/dyning")
public class DyningController {  
    
    private final DyningService dyningService;
    
    // // 제안
    // @GetMapping({"/{choice}", "/{choice}/list/","/{choice}/list"})
    // public String getList(@PathVariable("choice") String choice, Model model, PageRequestDTO pageRequestDTO) {
    //     log.info(choice+"list.................");
    //     model.addAttribute("result", dyningService.getList(pageRequestDTO));

    //     return "dyning/"+choice+"/list";
    // }


    // // 음식점 거리 페이지
    // @GetMapping("/restaurant/list")
    // public void restaurantList(Model model, PageRequestDTO pageRequestDTO) {
    //     log.info("restaurant list...........");
    //     model.addAttribute("result", dyningService.getList(pageRequestDTO));

    // }

    // 카페 거리 페이지
    @GetMapping("/cafe/list")
    public void cafeList(Model model) {
        log.info("cafe list.................");
        model.addAttribute("result", dyningService.getStreet());
    }

    

}
