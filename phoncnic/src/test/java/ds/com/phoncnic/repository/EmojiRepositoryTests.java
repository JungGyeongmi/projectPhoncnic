package ds.com.phoncnic.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.service.emoji.EmojiService;

@SpringBootTest
public class EmojiRepositoryTests {

    @Autowired
    EmojiRepository emojiRepository;
    @Autowired
    EmojiInfoRepository emojiInfoRepository;
    @Autowired
    EmojiService emojiService;

    
    @Test
    public void insertDummise() {
        
        IntStream.rangeClosed(1, 5).forEach(i -> {
            String[] emojiInfoUrlArrays = {
                "/phoncnic/display?fileName=2022%5C04%5C01%2Fd2343817-9931-440f-abbd-765f2f55ca4c_shock.png",
                "/phoncnic/display?fileName=2022%5C04%5C01%2F6ba9d059-b6cf-490e-8492-03b535f79ac9_heart.png",
                "/phoncnic/display?fileName=2022%5C04%5C01%2Ffe161d7b-9b9c-43c3-919e-a988f66de69d_love.png",
                "/phoncnic/display?fileName=2022%5C04%5C01%2Fec3610a5-9dbe-473f-99eb-2ee74add8e7f_cryingsmile.png",
                "/phoncnic/display?fileName=2022%5C04%5C01%2F4b3998e6-0e5c-4e52-9535-d6ec85262e94_good.png"
            };
            EmojiInfo emojiInfo = EmojiInfo.builder()
                .emojitype(i+"")
                .emojipath(emojiInfoUrlArrays[i-1])
                .kindofemoji("이모지"+i+"이여라")
            .build();

            emojiInfoRepository.save(emojiInfo);
        
        });
    }
    
    @Test
    @Transactional
    public void testGetEmojiListByMember() {
        List<Emoji> emojiList = emojiRepository.getEmojiByMember("user3@icloud.com");

        emojiList.stream().forEach(emoji -> {
            System.out.println(emojiService.entityToEmojiDTO(emoji));
        });

    }

    @Test
    public void testgetEnonType() {
        Emoji result = emojiRepository.getEnoAndType("user1@icloud.com", 13L);
        System.out.println(result);

    }

    @Test
    public void getEmojiCountgno1() {
        List<Object[]> result = emojiRepository.getEmojiCountByGno(30L);

        for (Object[] arr : result) {
            System.out.println("==========type");
            System.out.println(arr[0]);
            System.out.println("==========count");
            System.out.println(arr[1]);
        }
    }

    @Test
    @Transactional
    public void test() {
        List<EmojiDTO> emojiList = emojiService.getEmojiByGno("g", 3L);
        emojiList.forEach(emoji -> System.out.println(emoji));
    }

    @Test
    public void getEmojiCountgno() {
        List<Object[]> result = emojiRepository.getEmojiCountByGno(3L);
        Long[][] emojicntArr = new Long[5][2];
        for (int i = 0; i < emojicntArr.length; i++) {
            emojicntArr[i][0] = Long.valueOf(i + 1);
            emojicntArr[i][1] = Long.valueOf(0);
        }

        result.stream().forEach(obj -> {
            String type = (obj[0]).toString();
            Long count = (Long) (obj[1]);
            switch (type) {
                case "1":
                    emojicntArr[0][1] = count;
                    break;
                case "2":
                    emojicntArr[1][1] = count;
                    break;
                case "3":
                    emojicntArr[2][1] = count;
                    break;
                case "4":
                    emojicntArr[3][1] = count;
                    break;
                case "5":
                    emojicntArr[4][1] = count;
                    break;
            }
        });

        System.out.println(Arrays.deepToString(emojicntArr));

    }

    @Test
    public void testEmojitypeCwt() {
        Long emojicwt = emojiRepository.getEmojiCountByEmojitype(11L, "3");
        System.out.println(emojicwt);
    }

    @Test
    public void testEmojitypeCwt2() {
        List<Object[]> emojicwt = emojiRepository.getEmojiCountByEmojitype2(11L, "3");
        for (Object a : emojicwt)
            System.out.println(a);
    }

    @Test
    public void testCountEmojiList() {
        List<Object[]> emojiList = emojiRepository.getEmojiCountByGno(3L);

        emojiList.stream().forEach(cnt -> {
            System.out.println("count : " + cnt[1]);
            System.out.println("type : " + cnt[0]);
            System.out.println("count : " + cnt[1]);
        });
    }

    @Test
    @Transactional
    public void testUpdateEmoji() {
        Integer eno = emojiRepository.updateEmojiTypeByGnoAndMemberId("3", 28L, "user1@icloud.com");
        System.out.println(eno);
    }

 


}
