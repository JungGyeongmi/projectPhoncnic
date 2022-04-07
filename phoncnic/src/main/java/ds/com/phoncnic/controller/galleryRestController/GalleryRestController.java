package ds.com.phoncnic.controller.galleryRestController;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchPageRequestDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.FollowService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/gallery")
@Log4j2
public class GalleryRestController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private EmojiService emojiService;

    @Autowired
    private FollowService followService;

    @GetMapping("/curator")
    public ResponseEntity<PageResultDTO<GalleryDTO, Object[]>> getCuratorModal(SearchPageRequestDTO pageRequestDTO) {
        log.info("---------------get curator rest---------------");
        
        PageResultDTO<GalleryDTO, Object[]> result = galleryService.getGalleryPage(pageRequestDTO);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Gallery List
    @GetMapping("/read/{gno}")
    public ResponseEntity<GalleryDTO> getList(@PathVariable("gno") Long gno) {
        log.info("getgalleryList........gno" + gno);
        GalleryDTO galleryDTO = galleryService.getGallery(gno);
        log.info("galleryDTO : " + galleryDTO);
        return new ResponseEntity<>(galleryDTO, HttpStatus.OK);
    }

    // Emoji insert/update/remove
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/emoji/register/{gno}")
    public ResponseEntity<Long[][]> emojiRegister(@RequestBody EmojiDTO emojiDTO, @PathVariable("gno") Long gno, @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        emojiDTO.setGno(gno);
        emojiDTO.setId(authMemberDTO.getId());
        Long[][] newEmojiCount = emojiService.galleryEmojiRegiter(emojiDTO);
        
        log.info("emoji Register....................emojiDTO:" + emojiDTO);
        log.info(Arrays.deepToString(newEmojiCount));
       
        return new ResponseEntity<>(newEmojiCount, HttpStatus.OK);
    }
    
    // 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("follow/{artistname}/{loginUserId}")
    public ResponseEntity<Long> follow(@PathVariable String artistname,  @PathVariable String loginUserId) {
        log.info("gallery follow check ......");
        Long fno = 0L;
        if(loginUserId !=null) {
            fno = followService.getGalleryFno(loginUserId, artistname);
        }
        log.info("checked fno ..."+fno);
        return new ResponseEntity<>(fno, HttpStatus.OK);
    }
    
    // 등록 삭제
    @PreAuthorize("isAuthenticated()")
    @PostMapping("followRegister/{artistname}/{loginUserId}")
    public ResponseEntity<Object[]> followRegister(@RequestBody FollowDTO followDTO, @PathVariable String artistname, @PathVariable String loginUserId) {
        Object[] follow = null;

        if (loginUserId!="") {
            followDTO.setFollowerid(loginUserId);
    
            follow = followService.galleryfollowRegister(followDTO);
            
            log.info("follow Register followDTO:" +followDTO);
        } 
        return new ResponseEntity<>(follow, HttpStatus.OK);
    }
}
