package ds.com.phoncnic.controller.dyningRestController;


import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
public class DyningRestController {
    private final EmojiService emojiService;
   
}

