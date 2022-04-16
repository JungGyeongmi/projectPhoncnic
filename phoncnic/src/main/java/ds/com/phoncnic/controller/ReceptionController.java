package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reception")
public class ReceptionController {

  @GetMapping("/ceo")
  public String toCeoForm() {
    
    return "/application/ceoForm";
  }
  
  @GetMapping("/artist")
  public String toArtistForm() {
    
    return "/application/artistForm";
  }
  
  @PostMapping("/artist/submit")
  public String submitArtist() {
    
    return "redirect:/reception";
  }
}
