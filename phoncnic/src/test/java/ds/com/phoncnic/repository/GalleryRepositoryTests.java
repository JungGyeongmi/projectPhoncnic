package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
import ds.com.phoncnic.service.emoji.EmojiService;
import ds.com.phoncnic.service.gallery.GalleryService;

@SpringBootTest
public class GalleryRepositoryTests {

    @Autowired
    GalleryRepository galleryRepository;

    @Autowired
    GalleryService galleryService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EmojiRepository emojiRepository;
    
    @Autowired
    EmojiService emojiService;

    @Autowired
    EmojiInfoService emojiInfoService;

    @Autowired
    EmojiInfoRepository emojiInfoRepository;

    @Transactional
    @Commit
    @Test
    public void insertDummiesLatest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            
            String[] randomImageBasic = {
                "34058fc8-5d8b-403f-878b-4e95b2c264bb",
                "7fd1e6bb-3e6b-4488-8260-1d4c3ad36747",
                "b32cbeb3-adec-455a-a270-ec6831e75d29",
                "e9314e57-992a-46c3-909a-0f0dca7f4b08",
                "b7d375be-c025-4ab4-a0f9-2eca87cb7a94",
            };
            

            // gallery 추가
            boolean rand = (int) (Math.random() * 2) != 0;
            Gallery gallery = Gallery.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .artistid(memberRepository.findById("test" + (i+10) + "@icloud.com").get())
                    .imagepath("2022\\04\\01")
                    .imagetype(rand)
                    .imagename("testImage" + i + ".jpg")
                    .uuid(randomImageBasic[(int)Math.random()*5])
                    .build();
            galleryRepository.save(gallery);

            
            // emoji 추가
            List<Integer> randmember = new ArrayList<>();

            while (randmember.size() != 30) {
                int inputrandomNumber = (int) (Math.random() * 10) + 1;
                for (int k = 0; k < 10; k++) {
                    if (!randmember.contains(inputrandomNumber)) {
                        randmember.add(inputrandomNumber);
                        break;
                    }
                }
            }

            int ra = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < ra; j++) {
                Member member = memberRepository.findById("test" + randmember.get(j) + "@gmail.com").get();
                String emojiType = (int) (Math.random() * 5) + 1 + "";
                Emoji emoji = Emoji.builder()
                    .gallery(gallery)
                    .member(member)
                    .emojiInfo(emojiInfoRepository.findById(emojiType).get())
                    .build();
                emojiRepository.save(emoji);
            }
        });

    }


    @Transactional
    @Commit
    @Test
    public void insertDummise() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            List<Integer> randmember = new ArrayList<>();

            while (randmember.size() != 10) {
                int inputrandomNumber = (int) (Math.random() * 10) + 1;
                for (int k = 0; k < 10; k++) {
                    if (!randmember.contains(inputrandomNumber)) {
                        randmember.add(inputrandomNumber);
                        break;
                    }
                }
            }

            boolean rand = (int) (Math.random() * 2) != 0;
            Gallery gallery = Gallery.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .artistid(memberRepository.findById("test" + i + "@icloud.com").get())
                    .imagepath("2022\\03\\31")
                    .imagetype(rand)
                    .imagename("test" + i + ".jpg")
                    .uuid("946ed916-76d2-4039-b80d-97eb381866f6")
                    .build();
            galleryRepository.save(gallery);

            int ra = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < ra; j++) {
                Member member = memberRepository.findById("test" + randmember.get(j) + "@gmail.com").get();

                String emojiType = (int) (Math.random() * 5) + 1 + "";
                Emoji emoji = Emoji.builder()
                    .gallery(gallery)
                    .member(member)
                    .emojiInfo(emojiInfoRepository.findById(emojiType).get())
                    .build();
                emojiRepository.save(emoji);
            }
        });

    }
}