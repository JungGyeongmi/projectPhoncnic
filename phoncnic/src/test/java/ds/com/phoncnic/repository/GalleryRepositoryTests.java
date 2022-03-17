package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;
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
    EmojiInfoRepository emojiInfoRepository;

    @Transactional
    @Test
    @Commit
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
            
            Member member = Member.builder().id(memberRepository.findById("user" + i + "@icloud.com").get().getId()).build();
            
            boolean rand = (int)(Math.random()*2)!=0;
            Gallery gallery = Gallery.builder()
                .title(i + "title")
                .content(i + "content")
                .artistid(member)
                .imagepath("D:/image/imagepath/image" + i + ".jpg")
                .imagetype(rand)
                .imagename("imagename" + i)
                .build();
            galleryRepository.save(gallery);

            int ra = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < ra; j++) {
                member = Member.builder().id("user" + randmember.get(j) + "@icloud.com")
                    .build();

                String emojiType = (int) (Math.random() * 4) + 1 + "";
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
    public void getGalleryTest(){

        GalleryDTO galleryDTO = galleryService.getGallery(3L);
        System.out.println(galleryDTO);
    }

    @Test
    public void getGalleryList(){
        List<GalleryDTO> galleryDTOList = galleryService.getGalleryList(false);
        galleryDTOList.forEach(System.out::println);
    }

<<<<<<< HEAD
    @Test
    public void modifyTest() {

        Gallery gallery = galleryRepository.findById(2L).get();
        GalleryDTO dto = galleryService.entityToDTO(gallery);
        dto.setContent("content1004");
        dto.setTitle("title1004");
        System.out.println(dto.toString());
        galleryService.modify(dto);
        System.out.println(gallery.toString());
    }

=======
>>>>>>> gallery_ng
}
