package ds.com.phoncnic.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.Emoji;

@SpringBootTest
public class EmojiRepositoryTests {
    
    @Autowired
    private EmojiRepository emojiRepository;

    @Transactional
    @Test
    public void testGetEmojiListByMember() {
        
        List<Emoji> emojiList =  emojiRepository.getEmojiListByMember("user10@icloud.com");

        // emojiList.forEach(System.out::println);
        emojiList.forEach(emoji->{
            System.out.println(emoji.getEno());
            System.out.println(emoji.getMember());
            System.out.println(emoji.getEmojiInfo());

            // Optional<Emoji> testEmoji = emojiRepository.findById(emoji.getEno());
        });
    }
}
