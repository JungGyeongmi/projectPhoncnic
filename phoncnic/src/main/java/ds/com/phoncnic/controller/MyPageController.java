package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MyPageController {
    private final MemberService memberService;


    @GetMapping("/main/mypage")
    public void mypage(String id, Model model){
      log.info(id);
      // MemberDTO memberDTO = memberService.getMyPage(id);
      // model.addAttribute("memberDTO", memberDTO);
    }

    /* 제안 */
    @PostMapping("/main/mypage")
    public String toGoMypage(@RequestParam("id") String id, Model model){
        log.info("post "+id+"'s mypage...............");
        model.addAttribute("id", id);
        if(id != null) {
          MemberDTO memberDTO = memberService.getMyPage(id);
          model.addAttribute("memberDTO", memberDTO);
          model.addAttribute("id", id);
          // 1회성으로 넘기는 flash를 써도 좋을거같음
        }
        return "/main/mypage";
    }


    @PostMapping("/main/mypage/modify")
    public String modify(MemberDTO memberDTO, RedirectAttributes ra) {
      log.info("modify post.........memberDTO:"+memberDTO.getId());
      log.info("dto:"+ memberDTO);
  
      memberService.modify(memberDTO);
      ra.addAttribute("nickname", memberDTO.getNickname());
  
      return "redirect:/main/mypage";
    }
}
