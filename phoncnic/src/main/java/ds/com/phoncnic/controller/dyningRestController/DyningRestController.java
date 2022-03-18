package ds.com.phoncnic.controller.dyningRestController;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class DyningRestController {
    private final EmojiService emojiService;
    //이모지 추가
    @PostMapping("/dyning/emojiregister/{dno}")
    public ResponseEntity<Long> addEmoji(@RequestBody EmojiDTO EmojiDTO) {
        log.info("-----------------add Emoji-----------------");
        log.info("EmojiDTO:" + EmojiDTO);

        Long eno = emojiService.dyningEmojiRegister(EmojiDTO);
        return new ResponseEntity<>(eno, HttpStatus.OK);
        
    }
    //이모지 삭제
    @DeleteMapping("/dyning/emojiremove/{eno}")
    public ResponseEntity<Long> removeEmoji(@PathVariable Long eno) {
        log.info("eno:" + eno);

        emojiService.emojiRemove(eno);
        return new ResponseEntity<>(eno, HttpStatus.OK);

    }
}

