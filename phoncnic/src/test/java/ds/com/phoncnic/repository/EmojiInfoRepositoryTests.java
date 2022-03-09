package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.service.emoji.EmojiInfoService;

@SpringBootTest
public class EmojiInfoRepositoryTests {

    @Autowired
    EmojiInfoRepository emojiInfoRepository;

    @Autowired
    EmojiInfoService emojiInfoService;


    @Test
    public void insertDummise() {
        
        IntStream.rangeClosed(1, 5).forEach(i -> {

            EmojiInfo emojiInfo = EmojiInfo.builder()
                .emojitype(i+"")
                .emojipath("d://emoji/emojipath/emoji"+i+".jpg")
                .kindofemoji("이모지"+i+"이여라")
            .build();

            emojiInfoRepository.save(emojiInfo);
        
        });
    }

    @Test
    public void getEmojiInfoListTest() {
        
        emojiInfoService.getEmojiInfoList();

    }
}
