package ds.com.phoncnic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.service.mypage.CharacterLookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
public class HomeController {
    private final CharacterLookService characterLookService;

    @GetMapping({ "", "/" })
    public String home() {
        return "index";
    }

    @GetMapping("/crossroad")
    public String crossRoad() {
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

    @GetMapping("/lookmodal")
    public String test2 (String id, Model model) {
        log.info("id:" + id);
        
        model.addAttribute("id",id);
        model.addAttribute("hairDTO", characterLookService.getCharacterHair(id));
        model.addAttribute("clothesDTO", characterLookService.getCharacterClothes(id));
        model.addAttribute("looklist", characterLookService.lookimageList());

        return "/lookmodal";
    }

    @PostMapping("/lookmodal/lookmodify")
    public String lookmodify(CharacterLookDTO characterLookDTO, String id) {
        log.info("modify post.........:" + characterLookDTO.getHairname());
        log.info("modify post.........:" + characterLookDTO.getClothesname());

        characterLookService.modify(characterLookDTO, id);

        return "redirect:/";
    }
}
