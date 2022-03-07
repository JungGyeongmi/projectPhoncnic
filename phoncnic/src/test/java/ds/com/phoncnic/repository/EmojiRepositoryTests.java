package ds.com.phoncnic.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.service.EmojiService;

@SpringBootTest
public class EmojiRepositoryTests {
    
    @Autowired
    EmojiRepository emojiRepository;

    @Autowired
    EmojiService emojiService;

    @Test
    public void testGetEmojiListByMember() {
    
        Emoji emoji =  emojiRepository.getEmojiByMember("user3@icloud.com");
        
        EmojiDTO emojiDTO = emojiService.entityToEmojiDTO(emoji);
        
        System.out.println(emojiDTO);

    }
}
