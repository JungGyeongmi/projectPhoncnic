package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

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

            String[] randomImageBasic = {
                "061edec9-4bac-460a-93e6-d6c41b5b8abb",
                "017a59ec-da2e-47ec-b542-c1f98e88a1bc",
            };

            Member member = Member.builder().id("test" + (i+20) + "@gmail.com").build();

            // loof
            long roof = (long) (Math.random() * 5 + 1);

            RoofDesign roofDesign = RoofDesign.builder()
                    .oono(roof).build();

            // dyning
            Dyning dyning = Dyning.builder()
                    .dyningname("가게이름" + i)
                    .comment("사장님 한 마디" + i)
                    .location("부산 부산진구 중앙대로 668")
                    .locationdetails("6층")
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
                        .menuimageuuid(randomImageBasic[(int)Math.random()*2])
                        .menuimagepath("2022\\04\\01")
                        .backgroundname(j + "backgroundname.jpg")
                        .backgrounduuid(randomImageBasic[(int)Math.random()*2])
                        .backgroundpath("backgroundpath" + j)
                        .dyning(dyning)
                        .build();

                dyningImageRepository.save(dyningImage);
            }

            // emoji
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

            int ra = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < ra; j++) {
                member = Member.builder().id("test" + randmember.get(j) + "@gmail.com")
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
}
