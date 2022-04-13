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
                "061edec9-4bac-460a-93e6-d6c41b5b8abb",
                "017a59ec-da2e-47ec-b542-c1f98e88a1bc",
            };
            

            // gallery 추가
            boolean rand = (int) (Math.random() * 2) != 0;
            Gallery gallery = Gallery.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .artistid(memberRepository.findById("test" + (i+10) + "@gmail.com").get())
                    .imagepath("2022\\04\\01")
                    .imagetype(rand)
                    .imagename("testImage.jpg")
                    .uuid(randomImageBasic[(int)Math.random()*2])
                    .build();
            galleryRepository.save(gallery);

            
            // emoji 추가
            List<Integer> randmember = new ArrayList<>();

            while (randmember.size() != 10) {
                int inputrandomNumber = (int) (Math.random() * 30) + 1;
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
}