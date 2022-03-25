package ds.com.phoncnic.repository;

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
        List<Emoji> emojiList =  emojiRepository.getEmojiByMember("user3@icloud.com");

        emojiList.stream().forEach(emoji -> {
            System.out.println(emojiService.entityToEmojiDTO(emoji));
        });

    } 
    // @Test
    // public void getEmojiCountByGno (){
    //     Object result = emojiRepository.getEmojiCountByGno(3L);
    //     Object[] arr = (Object[])result;
    //     System.out.println(Arrays.toString(arr));


    // }

    // @Test
    // public void getEmojiCountgno(){
    //     List<Object[]> result = emojiRepository.getEmojiCountByGno(3L);

    //     for(Object[] arr : result){
    //         System.out.println(arr[0]);
    //         System.out.println(arr[1]);
    //     }

    //}
    @Test
    public void getEmojiCountgno(){
        List<Object[]> result = emojiRepository.getEmojiCountByGno(30L);

        for(Object[] arr : result){
            System.out.println("==========type");
            System.out.println(arr[0]);
            System.out.println("==========count");
            System.out.println(arr[1]);
            // System.out.println("==========type");
            // System.out.println(arr[2]);
            // System.out.println("==========count");
            // System.out.println(arr[3]);
        }

    }
    @Test
    @Transactional
    public void test(){
        

        List<EmojiDTO>  emojiList = emojiService.getEmojiByGno("g", 3L);
        emojiList.forEach(emoji ->System.out.println(emoji));
    }

}
