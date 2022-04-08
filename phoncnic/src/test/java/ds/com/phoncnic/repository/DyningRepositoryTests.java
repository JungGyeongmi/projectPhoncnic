package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
                "34058fc8-5d8b-403f-878b-4e95b2c264bb",
                "7fd1e6bb-3e6b-4488-8260-1d4c3ad36747",
                "b32cbeb3-adec-455a-a270-ec6831e75d29",
                "e9314e57-992a-46c3-909a-0f0dca7f4b08",
                "b7d375be-c025-4ab4-a0f9-2eca87cb7a94",
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
                        .menuimageuuid(randomImageBasic[(int)Math.random()*5])
                        .menuimagepath("2022\\04\\08")
                        .backgroundname(j + "backgroundname.jpg")
                        .backgrounduuid(randomImageBasic[(int)Math.random()*5])
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
