package ds.com.phoncnic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.dto.MyPageDTO;
import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.MyPageService;
import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService  myPageService;
    private final FollowService followService;
    private final EmojiService emojiService;

    @GetMapping("/main/mypage")
    public void mypage(String id, Model model){
        log.info("id:"+id);
        MyPageDTO mypageDTO = myPageService.getMyPage(id);
        FollowDTO followDTO = followService.getFollow(id);
        model.addAttribute("emojiDTOList", emojiService.getEmojiList(id));
        model.addAttribute("mypageDTO", mypageDTO);
        model.addAttribute("followDTO", followDTO);
    }   
}
