package ds.com.phoncnic.controller.dyningRestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.dto.FollowDTO;
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
    private final EmojiService emojiService;
    private final FollowService followService;

    @PostMapping("/details")
    public String restaurantDetails(@RequestParam("dno") Long dno, Model model) {
        log.info("restaurantDetails.................");
        model.addAttribute("result", dyningService.getDyningDetails(dno));
        model.addAttribute("imageresult", dyningService.getDyningDetails(dno).getDyningImageDTOList());
        return "redirect:/dyning/details";
    }

     //이모지 추가
     @PostMapping("/emojiregister/{dno}")
     public ResponseEntity<Long> addEmoji(@RequestBody EmojiDTO EmojiDTO) {
         log.info("-----------------add Emoji-----------------");
         log.info("EmojiDTO:" + EmojiDTO);
 
         Long eno = emojiService.dyningEmojiRegister(EmojiDTO);
         return new ResponseEntity<>(eno, HttpStatus.OK);
         
     }

     @PostMapping("/addfollow")
     public ResponseEntity<Long> addFollow(@RequestBody FollowDTO followDTO) {
         log.info("-----------------add Follow-----------------");
         log.info("followDTO:" + followDTO);
 
         Long fno = followService.addDyningFollow(followDTO);
         return new ResponseEntity<>(fno, HttpStatus.OK);
     }

   

     
     //이모지 삭제
     @DeleteMapping("/emojiremove/{eno}")
     public ResponseEntity<Long> removeEmoji(@PathVariable Long eno) {
         log.info("eno:" + eno);
 
         emojiService.emojiRemove(eno);
         return new ResponseEntity<>(eno, HttpStatus.OK);
 
     }

    // 팔로우 추가

    // 팔로우 삭제
    // @DeleteMapping("/details")

   
}
