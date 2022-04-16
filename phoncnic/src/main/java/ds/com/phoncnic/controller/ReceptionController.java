package ds.com.phoncnic.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ds.com.phoncnic.dto.ApplicationFormDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/reception")
public class ReceptionController {

  @GetMapping("/ceo")
  public String toCeoForm(@AuthenticationPrincipal AuthMemberDTO authDTO, Model model) {
    
    model.addAttribute("authDTO", authDTO);
    return "/application/ceoForm";
  }
  
  @GetMapping("/artist")
  public String toArtistForm(@AuthenticationPrincipal AuthMemberDTO authDTO, Model model) {
    
    model.addAttribute("authDTO", authDTO);
    return "/application/artistForm";
  }
  
  @PostMapping("/register")
  public String registerForm(ApplicationFormDTO applyDTO) {
    
    log.info("apply register....");
    log.info("applyDTO..."+applyDTO);
    return "redirect:/reception";
  }
}
