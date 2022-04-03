package ds.com.phoncnic.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.member.MemberService;
import ds.com.phoncnic.service.mypage.CharacterLookService;
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
    public void mypage( Model model,@AuthenticationPrincipal AuthMemberDTO dto) {
        log.info("id:" + dto.getId());
        MemberDTO memberDTO = memberService.getMember(dto.getId());
        model.addAttribute("id",dto.getId());
        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("hairDTO", characterLookService.getCharacterHair(dto.getId()));
        model.addAttribute("clothesDTO", characterLookService.getCharacterClothes(dto.getId()));
        model.addAttribute("looklist", characterLookService.lookimageList());
        model.addAttribute("emojiDTO", emojiService.getEmojiList(dto.getId()));
        model.addAttribute("afollowDTO", followService.getFollow(dto.getId()).getFollowartistlist());
        model.addAttribute("dfollowDTO", followService.getFollow(dto.getId()).getFollowdyninglist());
    }

    @PostMapping("/membermodify")
    public String membermodify(MemberDTO memberDTO, RedirectAttributes ra) {
        log.info("modify post.........id:" + memberDTO.getId());
        memberService.updateMemberDTO(memberDTO);
        ra.addAttribute("id", memberDTO.getId());
        return "redirect:/main/mypage";

    }

    @PostMapping("/lookmodify")
    public String lookmodify(CharacterLookDTO characterLookDTO, @AuthenticationPrincipal AuthMemberDTO dto, RedirectAttributes ra) {
        log.info("modify post.........:" + characterLookDTO.getHairname());
        log.info("modify post.........:" + characterLookDTO.getClothesname());

        characterLookService.modify(characterLookDTO, dto.getId());
        ra.addAttribute("id", dto.getId());
        return "redirect:/main/mypage";

    }

    @PostMapping("/memberremove")
    public String memberRemove(@AuthenticationPrincipal AuthMemberDTO dto) {
        log.info("member removing....." + dto.getId());
        memberService.remove(dto.getId());
        return "redirect:/";
    }

}
