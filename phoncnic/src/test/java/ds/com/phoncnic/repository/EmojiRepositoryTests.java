package ds.com.phoncnic.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.service.EmojiService;

@SpringBootTest
public class EmojiRepositoryTests {
    
    @Autowired
    EmojiRepository emojiRepository;

    @Autowired
    EmojiService emojiService;

    
    @Test
    @Transactional
    public void testGetEmojiListByMember() {
        List<Emoji> emojiList =  emojiRepository.getEmojiByMember("user3@icloud.com");
        // System.out.println(emojiList.size());
        emojiList.stream().forEach(emoji -> {
            System.out.println(emojiService.entityToEmojiDTO(emoji));
        });

    } 
}
