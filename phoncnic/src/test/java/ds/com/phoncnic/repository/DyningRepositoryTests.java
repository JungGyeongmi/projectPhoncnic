package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
                        .menuimagepath("menuimagepath" + j)
                        .backgroundname(j + "backgroundname.jpg")
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

    // @Test
    // public void getdynig(){
    //     List<Object[]> result = dyningRepository.getDyningDetails(11L);
    //     for(Object[] arr :result)System.out.println(Arrays.toString(arr));
<<<<<<< HEAD
    
=======
>>>>>>> T_main
    // }





}
