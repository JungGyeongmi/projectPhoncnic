package ds.com.phoncnic.controller;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.follow.FollowService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/main/mypage")
@RequiredArgsConstructor
@Log4j2
public class MyPageRestController {
    private final FollowService followService;
    private final EmojiService emojiService;
    private final GalleryService galleryService;

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

    @GetMapping("/gallery/{gno}")
    public ResponseEntity<GalleryDTO> getGalleryDetail(@PathVariable("gno") Long gno) {

        log.info("getgalleryList........gno" + gno);

        GalleryDTO galleryDTO = galleryService.getGallery(gno);
        log.info("galleryDTO : " + galleryDTO);
    
        return new ResponseEntity<>(galleryDTO, HttpStatus.OK);
    }

    @PostMapping("/emoji/{gno}")
    public ResponseEntity<String> emojiCheck(@PathVariable("gno") Long gno,
            @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {

        String type = emojiService.getEmojiTyoeByUserId(authMemberDTO.getId(), gno);
        
        log.info(type);
        
        if (type == null) {
            type = "";
        }

        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    @PostMapping("/emoji/register/{gno}")
    public ResponseEntity<Object[]> emojiRegister(
        @RequestBody EmojiDTO emojiDTO, 
        @PathVariable("gno") Long gno,
        @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        
      emojiDTO.setGno(gno);
      emojiDTO.setId(authMemberDTO.getId());
      
      Object[] returnResult = new Object[2];
      Long[][] newEmojiCount = emojiService.galleryEmojiRegiter(emojiDTO);
      Boolean checker = emojiService.checkExistEmoji(authMemberDTO.getId(), gno);
      
      returnResult[0] = checker;
      returnResult[1] = newEmojiCount;

      log.info("emoji Register....................emojiDTO:" + emojiDTO);
      log.info(Arrays.deepToString(newEmojiCount));

      return new ResponseEntity<>(returnResult, HttpStatus.OK);
    }



}
