package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.entity.RoofDesign;
import ds.com.phoncnic.service.dyning.DyningService;

@SpringBootTest
public class DyningRepositoryTests {

    @Autowired
    DyningRepository dyningRepository;

    @Autowired
    DyningImageRepository dyningImageRepository;

    @Autowired
    RoofDesignRepository roofDesignRepository;

    @Autowired
    EmojiRepository emojiRepository;

    @Autowired
    EmojiInfoRepository emojiInfoRepository;

    @Autowired
    DyningService dyningService;

    @Transactional
    @Commit
    @Test
    public void insertDyning() {

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

            Member member = Member.builder().id("user" + i + "@icloud.com").build();

            long roof = (long) (Math.random() * 5 + 1);

            RoofDesign roofDesign = RoofDesign.builder()
                    .oono(roof).build();

            Dyning dyning = Dyning.builder()
                    .dyningname("가게이름" + i)
                    .comment("사장님 한 마디" + i)
                    .location("실제가게위치" + i)
                    .locationdetails("1층")
                    .foodtype(roof)
                    .tel("051-1234-1234")
                    .businesshours("영업시간" + i)
                    .hashtag("해쉬태그" + i)
                    .ceoid(member)
                    .roofdesign(roofDesign)
                    .build();

            dyningRepository.save(dyning);

            for (int j = 0; j < Math.random() * 3; j++) {
                DyningImage dyningImage = DyningImage.builder()
                        .menuimagename(j + "menuimagename.jpg")
                        .menuimageuuid(UUID.randomUUID().toString())
                        .menuimagepath("menuimagepath" + j)
                        .backgroundname(j + "backgroundname.jpg")
                        .backgrounduuid(UUID.randomUUID().toString())
                        .backgroundpath("backgroundpath" + j)
                        .dyning(dyning)
                        .build();

                dyningImageRepository.save(dyningImage);
            }

            int ra = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < ra; j++) {
                member = Member.builder().id("user" + randmember.get(j) + "@icloud.com")
                        .build();

                String emojiType = (int) (Math.random() * 4) + 1 + "";
                Emoji emoji = Emoji.builder()
                        .dyning(dyning)
                        .member(member)
                        .emojiInfo(emojiInfoRepository.findById(emojiType).get())
                        .build();
                emojiRepository.save(emoji);
            }
        });
    }

    @Transactional
    @Test
    public void Test() {
        Optional<Dyning> result = dyningRepository.findById(1L);
        Object roof = result.get().getRoofdesign();
        System.out.println(roof);

    }

    @Test
    public void Test2() {
        List<Dyning> result = dyningRepository.getStreetList();
        result.forEach(i -> {
            System.out.println(i);
        });
    }

    @Test
    public void Test4() {
        List<DyningImage> result = dyningRepository.getImageDetailsPage(1L);
        result.forEach(i -> {
            System.out.println(i);
        });
    }

    @Test
    public void testSearchPage() {
        // Pageable pageable = PageRequest.of(0, 10, Sort.by("dno").descending().and(Sort.by("dyningname").ascending()));
        // Page<Object[]> result = dyningRepository.searchPage("n", "1", pageable);
    }

    @Test
    public void testFollowereCount() {
        Long followerCwt = dyningRepository.getDyningFollowerCount(1L);
        System.out.println(followerCwt);
    }

    @Test
    public void testt() {
        List<Object[]> result = dyningRepository.getDyningDetails(1L);
        System.out.println(Arrays.toString(result.get(1)));
    }

    @Transaction
    @Test
    public void testttt() {
        DyningDTO result = dyningService.getDyningDetails(1L);
        System.out.println(result);
        System.out.println();
    }
}
