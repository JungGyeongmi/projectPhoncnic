package ds.com.phoncnic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.MyPageDTO;
import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.MyPageService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/main")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService  myPageService;
    private final FollowService followService;
    private final EmojiService emojiService;
    private final GalleryService galleryService;

    @GetMapping("/mypage")
    public void mypage(String id, Model model){
        log.info("id:"+id);
        MyPageDTO mypageDTO = myPageService.getMyPage(id);
        FollowDTO followDTO = followService.getFollow(id);
        model.addAttribute("emojiDTOList", emojiService.getEmojiList(id));
        model.addAttribute("mypageDTO", mypageDTO);
        model.addAttribute("followDTO", followDTO);
    }

    @GetMapping("/galleryregister")
    public void getGalleryRegister(){
        /*
        mypage에 있던 유저 정보, id값을 그대로 가져와야함
        */
    }

    @PostMapping("/galleryregister")
    public String getRegisterGallery(GalleryDTO galleryDTO){
        log.info("galleryDTO "+galleryDTO.toString());
        galleryService.register(galleryDTO);
        /*
        동일하게 등록자의 정보, id를 그대로 전달해줘야함 

        */
        return "redirect:/main/mypage?id=user1@icloud.com";
    }
}
