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
                    // .uuid(UUID.randomUUID().toString())
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

    @Test
    public void getGalleryList() {
        List<GalleryDTO> galleryDTOList = galleryService.getGalleryList(false);
        // galleryDTOList.forEach(System.out::println);
        GalleryDTO galleryDTO = galleryDTOList.get(0);
        HashMap<String, String> resultHash = galleryDTO.getEmojiinfo();
        System.out.println(resultHash.get("1"));
        System.out.println(resultHash.get("2"));
        System.out.println(resultHash.get("3"));
        System.out.println(resultHash.get("4"));
        System.out.println(resultHash.get("5"));
    }


    @Test
    public void modifyTest() {
        List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();
        Gallery gallery = galleryRepository.findById(2L).get();

        GalleryDTO dto = galleryService.entityToDTO(gallery, emojiService.getEmojiCountArrayByGno(gallery.getGno()), emojiInfoList);
        dto.setContent("content1004");
        dto.setTitle("title1004");
        System.out.println(dto.toString());
        galleryService.modify(dto);
        System.out.println(gallery.toString());
    }

    @Test
    public void getPageByMemberId() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(3)
                .type("t")
                .keyword("3")
                .build();

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("gno"));

        Page<Gallery> result = galleryRepository.getGalleryPage(pageable);

        System.out.println(result.getSize());
        System.out.println(result.getTotalPages());
        System.out.println(result.hasNext());
        System.out.println(result.getContent());
    }

    @Test
    public void testSearch1() {
        galleryRepository.search1();
    }

    @Test
    public void testSearchPage() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("gno").descending());

        Page<Object[]> result = galleryRepository.searchPage("t", "title", pageable);

        List<Object[]> galleryList = result.getContent();

        Object[] gallery = galleryList.stream().toArray();

        System.out.println(Arrays.deepToString(gallery));
    }

    @Test
    public void getGalleryPageInModal() {
        SearchPageRequestDTO requestDTO = SearchPageRequestDTO.builder()
                .page(1)
                .size(3)
                .type("t")
                .keyword("title")
                .sort("gno")
                .build();

        PageResultDTO<GalleryDTO, Object[]> pageResult = galleryService.getGalleryPage(requestDTO);

        System.out.println(pageResult.getPage());
        System.out.println(pageResult.getSize());
        System.out.println(pageResult.getStart());
        System.out.println(pageResult.getDtoList());
    }

    @Test
    public void getGalleryDTOWithEmojiInfo() {

    }

}