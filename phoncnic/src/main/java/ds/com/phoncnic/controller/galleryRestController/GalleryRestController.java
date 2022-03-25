package ds.com.phoncnic.controller.galleryRestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/gallery")
public class GalleryRestController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private EmojiInfoService emojiInfoService;

    @Autowired
    private EmojiService emojiService;
    
    // @GetMapping("/read")
    // public String read (@RequestParam("gno") Long gno, Model model){
    //     log.info("read..............emoji:"+gno);
    //     if(gno != null){
    //     model.addAttribute("emoji", emojiService.getEmojiByGno(gno));
    //     } 
    //     return "redirect:/gallery/painting";
    // }


    //Gallery List
    @GetMapping("/read/{gno}")
    public ResponseEntity<GalleryDTO>getList(@PathVariable("gno") Long gno) {
         log.info("getgalleryList........gno" + gno);
         GalleryDTO galleryDTO = galleryService.getGallery(gno);
         log.info("galleryDTO : " + galleryDTO);
         return new ResponseEntity<>(galleryDTO, HttpStatus.OK);
         
}   
    //Emoji getList
    @GetMapping("/emoji/{gno}")
        public ResponseEntity<List<EmojiDTO>>getemojiList(@PathVariable("gno") Long gno) {
            log.info("getemojiList........gno" + gno);
            List<EmojiDTO> emojiDTO = emojiService.getEmojiByGno("g",gno);
            log.info("emojiDTO : " + emojiDTO);
            return new ResponseEntity<>(emojiDTO, HttpStatus.OK);
   
    }

    //Emoji Register
    @PostMapping("/register/{gno}")
    public ResponseEntity <Long> emojiRegister (@RequestBody EmojiDTO emojiDTO){
        log.info("emoji Register....................emojiDTO:"+emojiDTO);

        Long eno = emojiService.galleryEmojiRegiter(emojiDTO);
        return new ResponseEntity<>(eno, HttpStatus.OK);
    }

    // @PostMapping("/read/{gno}/all")
    // public ResponseEntity<GalleryDTO> getRead(@PathVariable("gno") Long gno) {
    //     log.info("post Gallery details......... rest " + gno);
    //     log.info(gno.getClass());
    //     GalleryDTO galleryDTO = galleryService.getGallery(gno);
    //     log.info("galleryDTO : " + galleryDTO);
    //     return new ResponseEntity<>(galleryDTO, HttpStatus.OK);
    // }

    // @PostMapping("/read/{gno}")
    // public ResponseEntity<EmojiDTO> getemoji(@PathVariable("gno") Long gno) {
    //     EmojiDTO emojiDTO = emojiService.getEmojiByGno(gno);
    //     return new ResponseEntity<>(emojiDTO, HttpStatus.OK);
    // }

}
