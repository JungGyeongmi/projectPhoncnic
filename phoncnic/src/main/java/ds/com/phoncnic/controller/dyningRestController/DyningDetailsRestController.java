package ds.com.phoncnic.controller.dyningRestController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.dyning.DyningService;
import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/dyning")
public class DyningDetailsRestController {

    private final DyningService dyningService;
    private final FollowService followService;
    private final EmojiService emojiService;

    @PostMapping("/details")
    public String restaurantDetails(@RequestParam("dno") Long dno, Model model) {
        log.info("restaurantDetails.................");
        model.addAttribute("result", dyningService.getDyningDetails(dno));
        model.addAttribute("imageresult", dyningService.getDyningDetails(dno).getDyningImageDTOList());
        return "redirect:/dyning/details";
    }

    // 팔로우 추가

    // 팔로우 삭제
    // @DeleteMapping("/details")

    // 이모지 추가

    // 이모지 삭제
}
