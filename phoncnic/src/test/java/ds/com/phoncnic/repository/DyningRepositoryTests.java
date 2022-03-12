package ds.com.phoncnic.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
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

    @Test
    public void insertDyning() {

        IntStream.rangeClosed(1, 10).forEach(i -> {

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

        });
    }

    @Transactional
    @Test
    public void Test() {
        Optional<Dyning> result = dyningRepository.findById(1L);
        Object roof = result.get().getRoofdesign();
        System.out.println(roof);

    }

    

}
