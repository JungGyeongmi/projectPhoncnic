package ds.com.phoncnic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class TestController {

    @Autowired
    private  GalleryService galleryService;
    @Autowired
    private EmojiInfoService emojiInfoService;


    @GetMapping("/test")
    public String test () {
        log.info("test....");
        return "test";
    }


}
