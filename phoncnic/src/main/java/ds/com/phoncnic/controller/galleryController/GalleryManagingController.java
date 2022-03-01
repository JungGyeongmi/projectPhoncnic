package ds.com.phoncnic.controller.galleryController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/gallery/setting")
public class GalleryManagingController {
    @GetMapping({"/", ""})
    public String gallerySetting(Model model) {
        return "/gallery/set/list";
    }

    @GetMapping("/register")
    public String register() {
        
        return "gallery/set/register";
    }

    @PostMapping("/register")
    public String register(Model model) { 
        log.info("register gallery image ...... "); 
        return "redirect:/gallery/setting";
    }

    @GetMapping("/read")
    public String read() {
        log.info("register read.......");
        return "gallery/set/read";
    }


    @PostMapping("/read/modify")
    public String modify(){
        log.info("modified......");
        return "redirect:/gallery/setting/read"; 
    }

    @PostMapping("/read/remove")
    public String remove() {
        log.info("removed......");
        return "redirect:/gallery/setting";
    }
}
