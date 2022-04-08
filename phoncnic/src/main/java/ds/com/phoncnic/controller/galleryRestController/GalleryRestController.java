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

    // emoji check
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/emoji/check/{gno}")
    public ResponseEntity<String> emojiChecker(@PathVariable("gno") Long gno,
            @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {

        String type = emojiService.getEmojiTyoeByUserId(authMemberDTO.getId(), gno);
        
        log.info(type);
        
        if (type == null) {
            type = "";
        }

        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    // Emoji insert/update/remove
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/emoji/register/{gno}")
    public ResponseEntity<Long[][]> emojiRegister(@RequestBody EmojiDTO emojiDTO, @PathVariable("gno") Long gno,
            @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        emojiDTO.setGno(gno);
        emojiDTO.setId(authMemberDTO.getId());
        Long[][] newEmojiCount = emojiService.galleryEmojiRegiter(emojiDTO);
        log.info("emoji Register....................emojiDTO:" + emojiDTO);
        log.info(Arrays.deepToString(newEmojiCount));

        return new ResponseEntity<>(newEmojiCount, HttpStatus.OK);
    }

    // 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("follow/{artistname}")
    public ResponseEntity<Long> follow(@PathVariable String artistname, @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("gallery follow check ......");
        Long fno = 0L;
        if (authMemberDTO.getId() != null) {
            fno = followService.getGalleryFno(authMemberDTO.getId(), artistname);
            log.info("fno come here" + fno);
        }
        log.info("checked fno ..." + fno);
        return new ResponseEntity<>(fno, HttpStatus.OK);
    }

    // 등록 삭제
    @PreAuthorize("isAuthenticated()")
    @PostMapping("followRegister/{artistname}")
    public ResponseEntity<Object[]> followRegister(@RequestBody FollowDTO followDTO, @PathVariable String artistname,
        @AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        Object[] follow = null;

        if (authMemberDTO.getId() != "") {
            log.info("loginUserId:" + authMemberDTO.getId());
            followDTO.setFollowerid(authMemberDTO.getId());
            follow = followService.galleryfollowRegister(followDTO);
        }
        return new ResponseEntity<>(follow, HttpStatus.OK);
    }
}
