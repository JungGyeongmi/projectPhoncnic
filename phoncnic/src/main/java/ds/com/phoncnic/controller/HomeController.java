package ds.com.phoncnic.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.characterLook.CharacterLookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class HomeController {

    private final CharacterLookService characterLookService;

    @GetMapping({ "", "/" })
    public String home(@AuthenticationPrincipal AuthMemberDTO dto, Model model) {
        if(dto != null) {
            log.info("dto get id " + dto.getId());
            model.addAttribute("loginuserId", dto.getId());
            model.addAttribute("avatar", characterLookService.getCharacterSet(dto.getId()));
        } else {
            model.addAttribute("avatar", characterLookService.getDefaultAvatar(1L));
        }
        return "main";
    }

    @GetMapping("/index")
    public void index() {
        log.info("index..");
    }

    @GetMapping("/reception")
    public String receptionDesk() {
        log.info("reception..");
        return "/application/reception";
    }

    @GetMapping("/crossroad")
    public String crossRoad( 
        HttpServletRequest request,
        HttpServletResponse response,
        Model model) {
        log.info("000000000000000");
        String referUrl =  request.getHeader("Referer");
        log.info(referUrl);
        model.addAttribute("referURL", referUrl);
        return "crossroad";
    }

    /*
     * dyning에서 restaurnat 랑 cafe가는 controller 어떻게 했는지 확인하고
     * url어떻게 받으면 좋을지 생각해봐야함
     */
    @GetMapping("/crossroad/gallery")
    public String crossRoadToGallery() {
        log.info("get gallery.......");
        
        return "redirect:/gallery";
    }

    // 이걸 한번에 받아서 DyningController에서 변수값 분기해주는 방법은 없나
    // dyning에서 여기서 한번 분기하고 뒤에 따라오는 변수값을 보내는 방법
    @GetMapping("/crossroad/dyning/{choice}")
    public String crossRoadToDyning(@PathVariable("choice") String choice) {
        log.info("get" + choice + ".......");
       
        return "redirect:/dyning/" + choice + "/list";
    }

    @GetMapping("/main/companyinfo")
    public String companyinfo() {
        return "/main/companyinfo";
    }

    @GetMapping("/manage/rolechoice")
    public String rolechoice() {
        return "/manage/rolechoice";
    }

    @GetMapping("/chat")
    public String chat2() {
        return "/ws/chat";
    }
  
    @GetMapping("/lookmodal")
    public String test2 (@AuthenticationPrincipal AuthMemberDTO dto, Model model) {
        if(dto!=null){
            log.info("id:" + dto.getId());
            model.addAttribute("id",dto.getId());
            model.addAttribute("setDTO", characterLookService.getCharacterSet(dto.getId()));
        } else{
            model.addAttribute("id","");
            model.addAttribute("setDTO", characterLookService.getCharacterSet("test10@gmail.com"));
        }
        model.addAttribute("looklist", characterLookService.lookimageList());
        return "lookmodal";
    }

    @PostMapping("/lookmodal/lookmodify")
    public String lookmodify(CharacterLookDTO characterLookDTO, @AuthenticationPrincipal AuthMemberDTO dto) {
        if(dto!=null){
            characterLookService.modify(characterLookDTO, dto.getId());
        }

        return "redirect:/";
    }

    @GetMapping("/websocket/chat")
    public String chat(Locale locale, Model model) {
        return "/websocket/chat";
    }


}
