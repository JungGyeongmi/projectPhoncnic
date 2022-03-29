package ds.com.phoncnic.repository;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.EmojiDTO;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.service.emoji.EmojiService;

@SpringBootTest
public class EmojiRepositoryTests {

    @Autowired
    EmojiRepository emojiRepository;

    @Autowired
    EmojiService emojiService;

    @Test
    @Transactional
    public void testGetEmojiListByMember() {
        List<Emoji> emojiList = emojiRepository.getEmojiByMember("user3@icloud.com");

        emojiList.stream().forEach(emoji -> {
            System.out.println(emojiService.entityToEmojiDTO(emoji));
        });

    }
<<<<<<< HEAD

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
            System.out.println("type : " + cnt[0]);
            System.out.println("count : " + cnt[1]);
        });
    }

    // @Test
    // public void GetEmojiByGnoAndId() {
    //     List<Emoji> emojiList = emojiRepository.getEmojiCheckByGnoAndId(1L, "user2@icloud.com" );

    //         emojiList.forEach(emoji -> System.out.println(emoji));

    
    // }

=======

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
>>>>>>> T_main
}
