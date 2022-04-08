package ds.com.phoncnic.controller.adminController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AdminController {
    
    @GetMapping("/admin")
    public void getAdminPage(Model model, @AuthenticationPrincipal AuthMemberDTO dto){
        model.addAttribute("userId", dto.getNickname());
        log.info("get admin...");
    }
}
