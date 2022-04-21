package ds.com.phoncnic.controller.galleryController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.characterLook.CharacterLookService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/gallery")
@RequiredArgsConstructor
@Log4j2
public class GalleryController {

    private final GalleryService galleryService;

    private final CharacterLookService characterLookService;

    @GetMapping({ "/", "" })
    public String galleryCrossroad() {
        log.info("GET crossgallery......");
        return "/gallery/crossgallery";
    }

    // 그림전 및 사진전 선택
    @GetMapping("/crossgallery/{choice}")
    public String crossgalleryPhoto(@PathVariable("choice") String choice) {
        log.info("dididididididi");
        log.info("get" + choice + ".......");
        return "redirect:/gallery/" + choice;
    }

    // 사진전 상세페이지
    @GetMapping("/photo")
    public String photo(
            PageRequestDTO pageRequestDTO,
            Model model, Long gno,
            @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {

        model.addAttribute("galleryDTOList", galleryService.getGalleryList(true));
        model.addAttribute("list", galleryService.getPaintingList(pageRequestDTO));

        log.warn("---------------" + authMemberDTO);
        log.warn(authMemberDTO);

        if (authMemberDTO != null) {
            String loginUserId = authMemberDTO.getId();
            String loginUserNick = authMemberDTO.getNickname();
            log.warn("id" + authMemberDTO.getId());
            model.addAttribute("userIdLogin", loginUserId);
            model.addAttribute("avatar", characterLookService.getCharacterSet(loginUserId));
            model.addAttribute("loginUserNick", loginUserNick);
        } else {
            String loginUserId = "";
            model.addAttribute("loginuserId", loginUserId);
            model.addAttribute("avatar", characterLookService.getDefaultAvatar(1L));
        }

        return "gallery/photo/list";
    }

    // 그림전 상세페이지
    @GetMapping("/painting")
    public String painting(
            PageRequestDTO pageRequestDTO,
            Model model,
            Long gno,
            @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {

        model.addAttribute("galleryDTOList", galleryService.getGalleryList(true));
        model.addAttribute("list", galleryService.getPaintingList(pageRequestDTO));

        log.warn("---------------" + authMemberDTO);
        log.warn(authMemberDTO);

        if (authMemberDTO != null) {
            String loginUserId = authMemberDTO.getId();
            String loginUserNick = authMemberDTO.getNickname();
            log.warn("id" + authMemberDTO.getId());
            model.addAttribute("userIdLogin", loginUserId);
            model.addAttribute("avatar", characterLookService.getCharacterSet(loginUserId));
            model.addAttribute("loginUserNick", loginUserNick);
        } else {
            String loginUserId = "";
            model.addAttribute("loginuserId", loginUserId);
            model.addAttribute("avatar", characterLookService.getDefaultAvatar(1L));
        }

        return "gallery/painting/list";
    }
}
