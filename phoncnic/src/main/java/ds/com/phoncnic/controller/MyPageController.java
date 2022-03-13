package ds.com.phoncnic.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.mypage.CharacterLookService;
import ds.com.phoncnic.service.mypage.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MyPageController {
    private final MemberService  memberService;
    private final CharacterLookService characterLookService;
    private final FollowService followService;
    private final EmojiService emojiService;

    @GetMapping("/main/mypage")
    public void mypage(String id, Model model){
        log.info("id:"+id);
        MemberDTO memberDTO = memberService.getMember(id);
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("emojiDTO", emojiService.getEmojiList(id));
        model.addAttribute("afollowDTO",followService.getFollow(id).getFollowartistlist());
        model.addAttribute("dfollowDTO",followService.getFollow(id).getFollowdyninglist());
        model.addAttribute("hairDTO",characterLookService.getCharacterHair(id));
        model.addAttribute("clothesDTO",characterLookService.getCharacterClothes(id));
    }   
}
