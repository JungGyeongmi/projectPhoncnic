package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchPageRequestDTO;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.EmojiInfo;
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
                    .artistid(memberRepository.findById("user" + i + "@icloud.com").get())
                    .imagepath("2022\\03\\31")
                    .imagetype(rand)
                    .imagename("test" + i + ".jpg")
                    .uuid("946ed916-76d2-4039-b80d-97eb381866f6")
                    .build();
            galleryRepository.save(gallery);

            int ra = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < ra; j++) {
                Member member = memberRepository.findById("user" + randmember.get(j) + "@icloud.com").get();

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

    @Test
    public void getGalleryTest() {
        GalleryDTO galleryDTO = galleryService.getGallery(3L);
        System.out.println("galleryDTO : " + galleryDTO);
        System.out.println("emojiCount : " + Arrays.deepToString(galleryDTO.getEmojicount()));
    }

   

}