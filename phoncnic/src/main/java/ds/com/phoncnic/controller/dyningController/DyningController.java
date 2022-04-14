package ds.com.phoncnic.controller.dyningController;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.characterLook.CharacterLookService;
import ds.com.phoncnic.service.dyning.DyningService;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;



@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/dyning")
public class DyningController {

    private final DyningService dyningService;
    private final EmojiService emojiService;
    private final EmojiInfoService emojiInfoService;
    private final FollowService followService;
    private final CharacterLookService characterLookService;
    private final MemberService memberService;

    // 카페 거리 페이지
    @GetMapping("/cafe/list")
    public void cafeList(Model model,@AuthenticationPrincipal AuthMemberDTO dto) {
        log.info("cafe list.................");
        model.addAttribute("result", dyningService.getCafeStreet());
       
        try {
            model.addAttribute("id",dto.getId());
            model.addAttribute("nickname",memberService.getNickname(dto.getId()));
            model.addAttribute("setDTO", characterLookService.getCharacterSet(dto.getId()));
        } catch (NullPointerException e) {
            model.addAttribute("id","");
            model.addAttribute("nickname","");
            model.addAttribute("setDTO", characterLookService.getCharacterSet("test1@gmail.com"));
        }
   

    }

    @GetMapping("/restaurant/list")
    public void restaurant(Model model,@AuthenticationPrincipal AuthMemberDTO dto) {
        log.info("restaurant list.................");
        model.addAttribute("result", dyningService.getRestaurantStreet());

        if(dto!=null){
            model.addAttribute("id",dto.getId());
            model.addAttribute("nickname",memberService.getNickname(dto.getId()));
            model.addAttribute("setDTO", characterLookService.getCharacterSet(dto.getId()));
        }else{
            model.addAttribute("id","");
            model.addAttribute("nickname","");
            model.addAttribute("setDTO", characterLookService.getCharacterSet("test1@gmail.com"));
        }

    }


    @GetMapping("/details")
    public void details(@RequestParam("dno") Long dno,@AuthenticationPrincipal AuthMemberDTO dto, Model model,
     @RequestParam("chartop") Long chartop,@RequestParam("charleft") Long charleft,RedirectAttributes ra) {

        log.info("Details.................");
        if (dno != 0) {
	        model.addAttribute("emojiInfo", emojiInfoService.getEmojiInfoList());
            DyningDTO dyningDTO = dyningService.getDyningDetails(dno);
            model.addAttribute("result", dyningService.getDyningDetails(dno));
            model.addAttribute("imageresult", dyningService.getDyningDetails(dno).getDyningImageDTOList());
            if(dto!=null){
                model.addAttribute("id", dto.getId());
                try {
                    model.addAttribute("fno", followService.getFno(dto.getId(), dyningDTO.getDyningname()));
                } catch (NullPointerException e) {
                    model.addAttribute("fno", "");
                }
                try {
                    model.addAttribute("eno", emojiService.HaveEmoji(dto.getId(), dno).getEno());
                    model.addAttribute("emojitype", emojiService.HaveEmoji(dto.getId(), dno).getEmojiInfo().getEmojitype());
                } catch (NullPointerException e) {
                    model.addAttribute("eno", "");
                    model.addAttribute("emojitype", "");
                }

            } else{
                model.addAttribute("eno", "");
                model.addAttribute("emojitype", "");
                model.addAttribute("id", "");
                model.addAttribute("fno", "");


            }
            model.addAttribute("emojilist", emojiService.dyningEmojiList(dno));
            model.addAttribute("emojitype1", emojiService.getEmojitypeCwt(dno, "1"));
            model.addAttribute("emojitype2", emojiService.getEmojitypeCwt(dno, "2"));
            model.addAttribute("emojitype3", emojiService.getEmojitypeCwt(dno, "3"));
            model.addAttribute("emojitype4", emojiService.getEmojitypeCwt(dno, "4"));
            model.addAttribute("emojitype5", emojiService.getEmojitypeCwt(dno, "5"));

            ra.addFlashAttribute("chartop", chartop);
            ra.addFlashAttribute("charleft",charleft);

        } else {
            return;
        }
    }




    @GetMapping("/movingtest")
    public String movingtest(Model model,@AuthenticationPrincipal AuthMemberDTO dto) {
        if(dto!=null){
            model.addAttribute("setDTO", characterLookService.getCharacterSet(dto.getId()));
        }else{
            model.addAttribute("setDTO", characterLookService.getCharacterSet("user10@icloud.com"));
        }


        return "/dyning/movingtest";
    }

    @GetMapping("/movingtest2")
    public String movingtest2(Model model,@AuthenticationPrincipal AuthMemberDTO dto) {
        if(dto!=null){
            model.addAttribute("setDTO", characterLookService.getCharacterSet(dto.getId()));
        }else{
            model.addAttribute("setDTO", characterLookService.getCharacterSet("user10@icloud.com"));
        }
        return "/dyning/movingtest2";
    }

    




}
