package ds.com.phoncnic.controller.galleryRestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/manage/gallery")
public class GallerySettingRestController {

    private EmojiService emojiService;
    private EmojiInfoService emojiInfoService;
    private GalleryService galleryService;

    // read and modify
    @GetMapping({ "/read" })
    public void getReadPage(long gno, Model model) {
        model.addAttribute("emojiList", emojiService.getEmojiList("g", gno));
        log.info("read emoji ..." + gno);
        model.addAttribute("emojiInfoList", emojiInfoService.getEmojiInfoList());
        model.addAttribute("galleryDTO", galleryService.getGallery(gno));
    }

    @GetMapping({ "/modify" })
    public void getModifyPage(long gno, Model model) {
        model.addAttribute("galleryDTO", galleryService.getGallery(gno));
    }

    @PostMapping("/modify")
    public String getRemovePage(GalleryDTO galleryDTO) {
        galleryService.modify(galleryDTO);
        log.info("modify page " + galleryDTO.getGno() + "....");
        return "redirect:/manage/gallery/read?gno=" + galleryDTO.getGno();
    }

    @PostMapping("/remove")
    public String getRemovePage(String id, long gno) {
        galleryService.removeWithEmojis(gno);
        log.info("remove page " + gno + "....");
        return "redirect:/manage/gallery/list"+id;
    }

}
