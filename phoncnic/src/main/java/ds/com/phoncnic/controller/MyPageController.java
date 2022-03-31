package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.mypage.CharacterLookService;
import ds.com.phoncnic.service.mypage.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/main/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MemberService memberService;
    private final CharacterLookService characterLookService;
    private final FollowService followService;
    private final EmojiService emojiService;

    @GetMapping({ "/", "" })
    public void mypage(String id, Model model) {
        log.info("id:" + id);
        MemberDTO memberDTO = memberService.getMember(id);
        model.addAttribute("id",id);
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("hairDTO", characterLookService.getCharacterHair(id));
        model.addAttribute("clothesDTO", characterLookService.getCharacterClothes(id));
        model.addAttribute("looklist", characterLookService.lookimageList());
        model.addAttribute("emojiDTO", emojiService.getEmojiList(id));
        model.addAttribute("afollowDTO", followService.getFollow(id).getFollowartistlist());
        model.addAttribute("dfollowDTO", followService.getFollow(id).getFollowdyninglist());
    }

    @PostMapping("/membermodify")
    public String membermodify(MemberDTO memberDTO, RedirectAttributes ra) {
        log.info("modify post.........id:" + memberDTO.getId());

        memberService.modify(memberDTO);
        ra.addAttribute("id", memberDTO.getId());
        return "redirect:/main/mypage";

    }

    @PostMapping("/lookmodify")
    public String lookmodify(CharacterLookDTO characterLookDTO, String id, RedirectAttributes ra) {
        log.info("modify post.........:" + characterLookDTO.getHairname());
        log.info("modify post.........:" + characterLookDTO.getClothesname());

        characterLookService.modify(characterLookDTO, id);
        ra.addAttribute("id", id);
        return "redirect:/main/mypage";

    }

    @PostMapping("/memberremove")
    public String memberRemove(String id) {
        log.info("member removing....." + id);
        memberService.remove(id);
        return "redirect:/";
    }

}
