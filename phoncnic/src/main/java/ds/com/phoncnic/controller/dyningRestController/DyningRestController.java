package ds.com.phoncnic.controller.dyningRestController;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/dyning/emojiregister/{dno}")
    public ResponseEntity<Long> addEmoji(@RequestBody EmojiDTO EmojiDTO) {
        log.info("-----------------add Emoji-----------------");
        log.info("EmojiDTO:" + EmojiDTO);

        Long eno = emojiService.dyningEmojiRegister(EmojiDTO);
        return new ResponseEntity<>(eno, HttpStatus.OK);
        
    }

}

