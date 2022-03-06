package ds.com.phoncnic.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.EmojiInfo;

@SpringBootTest
public class EmojiRepositoryTests {
    
    @Autowired
    private EmojiRepository emojiRepository;

    @Autowired
    private EmojiInfo emojiInfo;

    @Test
    void testGetEmojiByEmojiInfo() {
   
        
    }
}
