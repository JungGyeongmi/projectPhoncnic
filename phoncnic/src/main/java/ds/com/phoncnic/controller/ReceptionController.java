package ds.com.phoncnic.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.ApplicationFormDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.reception.ApplicationFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/reception")
public class ReceptionController {

  private final ApplicationFormService applicationFormService;

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
  public String registerForm(ApplicationFormDTO applyDTO, RedirectAttributes ra) {
    
    log.info("apply register....");
    
    Long afno = applicationFormService.isItMaxReception(applyDTO.getApplicant());

    if( afno == 0L ) {
      afno = applicationFormService.register(applyDTO);
      ra.addFlashAttribute("afno", afno);
    }
    ra.addFlashAttribute("userId", applyDTO.getApplicant());

    return "redirect:/reception";
  }
}
