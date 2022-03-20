package ds.com.phoncnic.controller.galleryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/manage/gallery")
public class GallerySettingController {
    

    @Autowired
    GalleryService galleryService;

    @GetMapping("/register")
    public void getRegister(String id) {
        log.info("go to register page.....");
    }

    @PostMapping("/register") 
    public String getRegisetr(String id, GalleryDTO galleryDTO) {
        log.info("register...."+galleryDTO.getGno());
        galleryService.register(galleryDTO);
        return "redirect:/main/mypage?id="+id;
    }

}
