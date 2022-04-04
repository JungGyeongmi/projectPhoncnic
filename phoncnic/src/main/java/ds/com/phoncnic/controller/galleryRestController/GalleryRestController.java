package ds.com.phoncnic.controller.galleryRestController;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    
    // Emoji getList
    @GetMapping("/emoji/{gno}")
    public ResponseEntity<List<EmojiDTO>> getemojiList(@PathVariable("gno") Long gno) {
        log.info("getemojiList........gno" + gno);

        List<EmojiDTO> emojiDTO = emojiService.getEmojiByGno("g", gno);
        
        log.info("emojiDTO : " + emojiDTO);
        
        return new ResponseEntity<>(emojiDTO, HttpStatus.OK);
    }

    // Emoji insert/update/remove
    @PostMapping("/emoji/register/{gno}")
    public ResponseEntity<Long[][]> emojiRegister(@RequestBody EmojiDTO emojiDTO, @PathVariable("gno") Long gno) {
        emojiDTO.setGno(gno);
        Long[][] newEmojiCount = emojiService.galleryEmojiRegiter(emojiDTO);
        
        log.info("emoji Register....................emojiDTO:" + emojiDTO);
        log.info(Arrays.deepToString(newEmojiCount));
       
        return new ResponseEntity<>(newEmojiCount, HttpStatus.OK);
    }
    

    @GetMapping("follow/{id}/{artistname}")
    public ResponseEntity<Long> follow(@PathVariable String id, @PathVariable String artistname) {
        Long fno = followService.getGalleryFno(id, artistname);
        return new ResponseEntity<>(fno, HttpStatus.OK);
    }

    @PostMapping("followRegister/{id}/{artistname}")
    public ResponseEntity<Object[]> followRegister(@RequestBody FollowDTO followDTO, @PathVariable String artistname, @PathVariable String id) {
        Object[] follow = followService.galleryfollowRegister(followDTO);
        log.info("follow Register followDTO:" +followDTO);
        return new ResponseEntity<>(follow, HttpStatus.OK);


    }
}
