package ds.com.phoncnic.repository;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        List<Emoji> emojiList =  emojiRepository.getEmojiByMember("user3@icloud.com");

        emojiList.stream().forEach(emoji -> {
            System.out.println(emojiService.entityToEmojiDTO(emoji));
        });

    } 

    @Test
    public void testEmojitypeCwt() {
        Long emojicwt = emojiRepository.getEmojiCountByEmojitype(11L, "3");
        System.out.println(emojicwt);
    }

    @Test
    public void testEmojitypeCwt2() {
        List<Object[]> emojicwt = emojiRepository.getEmojiCountByEmojitype2(11L, "3");
        for(Object a: emojicwt)System.out.println(a);
        }

    /*@Test
    public void getCountEmoji(){
        // List<Object[]> result = EmojiRepository.getEmojiCountByGno(1L);
        // testResult.forEach(System.out::println);    
        // System.out.println(result);
        for (Object[] arr : result ) {
            System.out.println(Arrays.toString(arr));
            if(arr[1]!=null){

                System.out.println(arr[0]);
                System.out.println(arr[1]);
            }
            System.out.println("============");
            //  System.out.println(arr[0]); //emojitype
        }
    }*/
}