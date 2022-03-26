package ds.com.phoncnic.controller.dyningController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ds.com.phoncnic.service.dyning.DyningService;
import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/dyning")
public class DyningController {

    private final DyningService dyningService;
    private final EmojiService emojiService;

    // 카페 거리 페이지
    @GetMapping("/cafe/list")
    public void cafeList(Model model) {
        log.info("cafe list.................");
        model.addAttribute("result", dyningService.getStreet());
    }

    @GetMapping("/restaurant/list")
    public void restaurant(Model model) {
        log.info("restaurant list.................");
        model.addAttribute("result", dyningService.getStreet());
    }

    @GetMapping("/details")
    public void details(@RequestParam("dno") Long dno, Model model) {
        log.info("Details.................");
        if(dno!=0){
        model.addAttribute("result", dyningService.getDyningDetails(dno));
        model.addAttribute("imageresult", dyningService.getDyningDetails(dno).getDyningImageDTOList());
        model.addAttribute("emojilist", emojiService.dyningEmojiList(dno));
        model.addAttribute("emojitype1",emojiService.getEmojitypeCwt(dno, "1"));
        model.addAttribute("emojitype2",emojiService.getEmojitypeCwt(dno, "2"));
        model.addAttribute("emojitype3",emojiService.getEmojitypeCwt(dno, "3"));
        model.addAttribute("emojitype4",emojiService.getEmojitypeCwt(dno, "4"));
        model.addAttribute("emojitype5",emojiService.getEmojitypeCwt(dno, "5"));

        }else return;
        
    }

}
