package ds.com.phoncnic.controller.galleryController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.GalleryDTO;
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
    private final EmojiService emojiService;
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
        // model.addAttribute("list", galleryService.getPhotoList(pageRequestDTO));
        model.addAttribute("galleryDTOList", galleryService.getGalleryList(false));
        model.addAttribute("emoji", emojiService.getEmojiList("g"));
        model.addAttribute("list", galleryService.getPhotoList(pageRequestDTO));
        return "gallery/photo/list";
    }

    //그림전 상세페이지
    @GetMapping("/painting")
    public String painting(PageRequestDTO pageRequestDTO, Model model){
        // model.addAttribute("list", galleryService.getPaintingList(pageRequestDTO));
        model.addAttribute("galleryDTOList", galleryService.getGalleryList(true));
        model.addAttribute("emoji", emojiService.getEmojiList("g"));
        model.addAttribute("list", galleryService.getPaintingList(pageRequestDTO));
        return "gallery/painting/list";
    }

    @GetMapping("/{choice}/{check}")
    public String getReadPage(@PathVariable("choice") String choice, @PathVariable("check") String check, long gno, PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("emojiList", emojiService.getEmojiList("g", gno));
        log.info("read emoji ..." + gno);

        
        model.addAttribute("emojiInfoList", emojiInfoService.getEmojiInfoList());
        model.addAttribute("galleryDTO", galleryService.getGallery(gno));

        return "/gallery/"+choice+"/"+check;
    }

    @PostMapping("/{choice}/remove")
    public String getRemovePage(@PathVariable("choice") String choice, long gno){
        
        galleryService.removeWithEmojis(gno);

        log.info("remove "+choice+" page "+gno+"....");
        return "redirect:/gallery/"+choice;
    }

    @PostMapping("/{choice}/modify")
    public String getRemovePage(@PathVariable("choice") String choice, GalleryDTO galleryDTO, long gno){
        
        galleryService.modify(galleryDTO);

        log.info("modify "+choice+" page "+galleryDTO.getGno()+"....");
        return "redirect:/gallery/"+choice+"/read?gno="+gno;
    }

}
