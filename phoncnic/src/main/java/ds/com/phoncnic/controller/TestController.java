package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class TestController {

    private  GalleryService galleryService;


    @GetMapping("/test")
    public String test () {
        log.info("test....");
        return "test";
    }

    // @GetMapping("/toggle")
    // public String slide(){
    //     return "toggleTest";
    // }

    @GetMapping("/toggle")
    public String painting( PageRequestDTO pageRequestDTO, Model model, Long gno){
        model.addAttribute("galleryDTOList", galleryService.getGalleryList(true));
        model.addAttribute("list", galleryService.getPaintingList(pageRequestDTO));
        return "toggleTest";
    }

}
