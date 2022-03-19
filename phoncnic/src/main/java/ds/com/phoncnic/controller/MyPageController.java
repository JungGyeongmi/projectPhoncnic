package ds.com.phoncnic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.MyPageDTO;
=======
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.dto.MemberDTO;
>>>>>>> T_main
import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.emoji.EmojiService;
<<<<<<< HEAD
import ds.com.phoncnic.service.gallery.GalleryService;
=======
import ds.com.phoncnic.service.mypage.CharacterLookService;
import ds.com.phoncnic.service.mypage.MemberService;
>>>>>>> T_main
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
<<<<<<< HEAD
@RequestMapping("/main")
=======
@RequestMapping("/main/mypage")
>>>>>>> T_main
@RequiredArgsConstructor
public class MyPageController {
    private final MemberService  memberService;
    private final CharacterLookService characterLookService;
    private final FollowService followService;
    private final EmojiService emojiService;
    private final GalleryService galleryService;

<<<<<<< HEAD
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
=======
    @GetMapping({"/",""})
    public void mypage(String id, Model model){
        log.info("id:"+id);
        MemberDTO memberDTO = memberService.getMember(id);
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("hairDTO",characterLookService.getCharacterHair(id));
        model.addAttribute("clothesDTO",characterLookService.getCharacterClothes(id));
        model.addAttribute("emojiDTO", emojiService.getEmojiList(id));
        model.addAttribute("afollowDTO",followService.getFollow(id).getFollowartistlist());
        model.addAttribute("dfollowDTO",followService.getFollow(id).getFollowdyninglist());
    }   

    @PostMapping("/membermodify")
        public String membermodify(MemberDTO memberDTO, RedirectAttributes ra) {
            log.info("modify post.........id:"+memberDTO.getId());
            
            memberService.modify(memberDTO);
            ra.addAttribute("id", memberDTO.getId());
            return "redirect:/main/mypage";

    }
    @PostMapping("/lookmodify")
        public String lookmodify(CharacterLookDTO characterLookDTO,String id, RedirectAttributes ra) {
            log.info("modify post.........:"+characterLookDTO.getHairname());
            log.info("modify post.........:"+characterLookDTO.getClothesname());
            
            characterLookService.modify(characterLookDTO,id);
            ra.addAttribute("id", id);
            return "redirect:/main/mypage";

    }
    @PostMapping("/memberremove")
    public String memberRemove(String id){
        log.info("member removing....."+id);
        memberService.remove(id);
        return "redirect:/";
    }

    
>>>>>>> T_main
}
