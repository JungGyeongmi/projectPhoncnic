package ds.com.phoncnic.controller.galleryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/manage/gallery")
public class GallerySettingController {
    
    @Autowired
    GalleryService galleryService;

    @Autowired
    EmojiService emojiService;
    
    @Autowired
    EmojiInfoService emojiInfoService;


    @GetMapping("/list")
    public void toListPage(String id, Model model) {
        log.info("user id : "+id+" list page.....");
        model.addAttribute("galleryDTOList", galleryService.getUserGallery(id));
        log.info("gallery list : "+galleryService.getUserGallery(id));
    }

    @GetMapping("/register")
    public void toRegister(String id) {
        log.info("register page.....");
    }

    @PostMapping("/register") 
    public String getRegisetr(String id, GalleryDTO galleryDTO) {
        log.info("register...."+galleryDTO.getGno());
        galleryService.register(galleryDTO);
        return "redirect:/main/mypage?id="+id;
    }

    // read modify remove 모두 여기로 이동
 // read and modify
    @GetMapping({ "/read","/modify" })
    public void getReadPage(long gno, Model model,PageRequestDTO pageRequestDTO) {
        model.addAttribute("emojiList", emojiService.getEmojiList("g", gno));
        log.info("read emoji ..." + gno);
        model.addAttribute("emojiInfoList", emojiInfoService.getEmojiInfoList());
        model.addAttribute("galleryDTO", galleryService.getGallery(gno));
    }

    // // read 후에 수정되는 부분을 어떻게 처리해야하는지
    // @GetMapping({ "/modify" })
    // public void getModifyPage(long gno, Model model, PageRequestDTO pageRequestDTO) {

    //     model.addAttribute("galleryDTO", galleryService.getGallery(gno));
    // }

    @PostMapping("/modify")
    public String getModifyPage(GalleryDTO galleryDTO, RedirectAttributes ra, Long gno,PageRequestDTO pageRequestDTO) {
        // galleryService.modify(galleryDTO);
        // log.info("modify page " + galleryDTO.getGno() + "....");
        // return "redirect:/manage/gallery/read?gno=" + galleryDTO.getGno();

        galleryService.modify(galleryDTO);

        ra.addAttribute("gno", galleryDTO.getGno());

        ra.addAttribute("page", pageRequestDTO.getPage());
        ra.addAttribute("type", pageRequestDTO.getType());
        ra.addAttribute("keyword", pageRequestDTO.getKeyword());

        return "redirect:/manage/gallery/read";

    }

    @PostMapping("/remove")
    public String getRemovePage(String id, long gno) {
        galleryService.removeWithEmojis(gno);
        log.info("remove page " + gno + "....");
        return "redirect:/manage/gallery/list"+id;
    }

}
