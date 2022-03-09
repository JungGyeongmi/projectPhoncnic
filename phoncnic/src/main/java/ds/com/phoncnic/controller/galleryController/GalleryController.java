package ds.com.phoncnic.controller.galleryController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
@Log4j2
public class GalleryController {

    private final GalleryService galleryService;
<<<<<<< HEAD
=======
    private final EmojiService emojiService;
>>>>>>> gallery_ng
    private final EmojiInfoService emojiInfoService;

    @GetMapping({"/", ""})
    public String galleryCrossroad() {
        log.info("GET crossgallery......");
        return "/gallery/crossgallery";
    }

    // 그림전 및 사진전 선택
    @GetMapping("/crossgallery/{choice}")
    public String crossgalleryPhoto(@PathVariable("choice") String choice){
        log.info("get"+choice+".......");
        return "redirect:/gallery/"+choice;
    }
    
    //사진전 상세페이지
    @GetMapping("/photo")
    public String photo(PageRequestDTO pageRequestDTO, Model model){
<<<<<<< HEAD
        // model.addAttribute("list", galleryService.getPhotoList(pageRequestDTO));
        model.addAttribute("galleryDTOList", galleryService.getGalleryList(false));
=======
        model.addAttribute("emoji", emojiService.getEmojiList("g"));
        model.addAttribute("list", galleryService.getPhotoList(pageRequestDTO));
>>>>>>> gallery_ng
        return "gallery/photo";
    }

    //그림전 상세페이지
    @GetMapping("/painting")
    public String painting(PageRequestDTO pageRequestDTO, Model model){
<<<<<<< HEAD
        // model.addAttribute("list", galleryService.getPaintingList(pageRequestDTO));
        model.addAttribute("galleryDTOList", galleryService.getGalleryList(true));
=======
        model.addAttribute("emoji", emojiService.getEmojiList("g"));
        model.addAttribute("list", galleryService.getPaintingList(pageRequestDTO));
>>>>>>> gallery_ng
        return "gallery/painting";
    }

    @GetMapping("/read/{gno}")
    public String getReadPage(@PathVariable("gno")long gno, PageRequestDTO pageRequestDTO, Model model){
<<<<<<< HEAD
=======
        model.addAttribute("emojiList", emojiService.getEmojiList("g", gno));
        // log.info("read emoji ..." + emojiService.getEmojiList("g", gno));
>>>>>>> gallery_ng
        
        model.addAttribute("emojiInfoList", emojiInfoService.getEmojiInfoList());
        model.addAttribute("galleryDTO", galleryService.getGallery(gno));

        return "/gallery/read";
    }


}
