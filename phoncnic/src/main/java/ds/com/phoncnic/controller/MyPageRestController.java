package ds.com.phoncnic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/main/mypage")
@RequiredArgsConstructor
@Log4j2
public class MyPageRestController {
    private final FollowService followService;
    private final EmojiService emojiService;

    @DeleteMapping("/artistremove/{id}/{artistname}")
    public ResponseEntity<String> removeFollowArtist(@PathVariable String id, @PathVariable String artistname) {
        log.info("artistname:" + artistname);

        followService.removeArtistFollow(id, artistname);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @DeleteMapping("/dyningremove/{id}/{dyningname}")
    public ResponseEntity<String> removeFollowDyning(@PathVariable String id, @PathVariable String dyningname) {
        log.info("artistname:" + dyningname);

        followService.removeDyningFollow(id, dyningname);
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @DeleteMapping("/emojiremove/{eno}")
    public ResponseEntity<Long> removeEmoji(@PathVariable Long eno) {
        log.info("eno:" + eno);

        emojiService.emojiRemove(eno);
        return new ResponseEntity<>(eno, HttpStatus.OK);

    }

    // @PostMapping("/main/mypage/artistremove")
    // public String artistremove(String id,String name, RedirectAttributes ra) {
    // // log.info("remove name.........:"+name);
    // // log.info("id.........:"+id);

    // followService.removeArtistFollow(id,name);
    // ra.addAttribute("id", id);
    // return "redirect:/main/mypage";

    // }
    // @PostMapping("/main/mypage/dyningremove")
    // public String dyningremove(String id,String name, RedirectAttributes ra) {
    // log.info("id.........:"+id);
    // log.info("remove name.........:"+name);

    // followService.removeDyningFollow(id,name);
    // ra.addAttribute("id", id);
    // return "redirect:/main/mypage";

    // }
}
