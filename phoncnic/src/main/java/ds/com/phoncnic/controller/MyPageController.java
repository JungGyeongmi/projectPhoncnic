 package ds.com.phoncnic.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.characterLook.CharacterLookService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.follow.FollowService;
import ds.com.phoncnic.service.member.MemberService;
import ds.com.phoncnic.service.reception.ApplicationFormService;
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
    private final ApplicationFormService applicationFormService;

    @GetMapping({ "/", "" })
    public void mypage(Model model, @AuthenticationPrincipal AuthMemberDTO dto) {

        log.info("----------------");
        MemberDTO memberDTO = memberService.getMember(dto.getId());
        log.info(memberDTO.getRoleSet());
        model.addAttribute("id",dto.getId());
        model.addAttribute("memberDTO", memberDTO);

        model.addAttribute("setDTO", characterLookService.getCharacterSet(dto.getId()));
        model.addAttribute("looklist", characterLookService.lookimageList());
        model.addAttribute("emojiDTO", emojiService.getEmojiList(dto.getId()));
        model.addAttribute("afollowDTO", followService.getFollow(dto.getId()).getFollowartistlist());
        model.addAttribute("dfollowDTO", followService.getFollow(dto.getId()).getFollowdyninglist());
        model.addAttribute("applyDTO", applicationFormService.applicationExistsCheckerByUserId(dto.getId()));
    }

    @PostMapping("/membermodify")
    public String membermodify(MemberDTO memberDTO, 
        RedirectAttributes ra, 
        @AuthenticationPrincipal AuthMemberDTO auth,
        HttpSession session) {
        
        String nickname = memberDTO.getNickname();
        log.info("update....");
        
        if(memberService.nickNameChecker(nickname)){
            ra.addFlashAttribute("msg", "false");
            return  "redirect:/main/mypage";
        }

        memberDTO.setRoleSet(auth.getAuthorities());
        log.info("memberDTO : "+memberDTO);

        memberService.updateMemberDTO(memberDTO);
        log.info("change....");

        // session 값
        auth.setNickname(nickname);
        Authentication authentication = new UsernamePasswordAuthenticationToken(auth, null, auth.getAuthorities());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        ra.addFlashAttribute("msg", "true");
        return "redirect:/main/mypage";
    }

    @PostMapping("/lookmodify")
    public String lookmodify(CharacterLookDTO characterLookDTO, @AuthenticationPrincipal AuthMemberDTO dto, RedirectAttributes ra) {
        log.info("modify post.........:" + characterLookDTO.getSetname());

        characterLookService.modify(characterLookDTO, dto.getId());
        ra.addAttribute("id", dto.getId());
        return "redirect:/main/mypage";
    }

    @PostMapping("/memberremove")
    public String memberRemove(String id,HttpSession session) {
        log.info("member removing....." + id);
        memberService.remove(id);
        session.invalidate();
        return "redirect:/";
    }
}
