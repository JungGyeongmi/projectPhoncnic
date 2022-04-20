package ds.com.phoncnic.controller.galleryController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
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


    @GetMapping({"/", ""})
    public String toMainManagePage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        String id = authMemberDTO.getId();
        String nickname = authMemberDTO.getNickname();
        log.info("user id : "+id+" list page.....");

        model.addAttribute("nickname", nickname);
        model.addAttribute("galleryDTOList", galleryService.getUserGallery(id));
        log.info("gallery list : "+galleryService.getUserGallery(id));
        return "/manage/gallery/manage";
    }

    @GetMapping("/list")
    public void toListPage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        String id = authMemberDTO.getId();
        log.info("user id : "+id+" list page.....");

        model.addAttribute("galleryDTOList", galleryService.getUserGallery(id));
        log.info("gallery list : "+galleryService.getUserGallery(id));
    }

    @GetMapping("/register")
    public void toRegister(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, Model model) {
        model.addAttribute("authDTO", authMemberDTO);
        log.info("register page.....");
    }

    @PostMapping("/register") 
    public String getRegisetr(GalleryDTO galleryDTO, RedirectAttributes ra) {
        log.info("register...."+galleryDTO);
        log.info(galleryService.isItmaxLength(galleryDTO.getId()));
        if(galleryService.isItmaxLength(galleryDTO.getId())){
            Long gno = galleryService.register(galleryDTO);
            ra.addFlashAttribute("gno", gno);
        } else {
            ra.addFlashAttribute("gno", "초과");
        }
      
        return "redirect:/manage/gallery/list?id="+galleryDTO.getId();
    }

    // read and modify
    @GetMapping({ "/read","/modify" })
    public void getReadPage(long gno, Model model) {
        log.info("read emoji ..." + gno);
        model.addAttribute("emojiInfoList", emojiInfoService.getEmojiInfoList());
        model.addAttribute("galleryDTO", galleryService.getGallery(gno));
    }

    @PostMapping("/modify")
    public String getRemovePage(GalleryDTO galleryDTO) {

        galleryService.modify(galleryDTO);

        log.info("modify page " + galleryDTO.getGno() + "....");
        return "redirect:/manage/gallery/read?gno=" + galleryDTO.getGno();
    }

    @PostMapping("/remove")
    public String getRemovePage(@AuthenticationPrincipal AuthMemberDTO authMemberDTO, long gno) {
        galleryService.removeWithEmojis(gno);
        log.info("remove page " + gno + "....");
        return "redirect:/manage/gallery/list?id="+authMemberDTO.getId();
    }

}
