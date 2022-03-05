package ds.com.phoncnic.repository;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.entity.RoofDesign;
import ds.com.phoncnic.entity.RoofDesignInfo;

@SpringBootTest
public class DyningRepositoryTests {

    @Autowired
    DyningRepository dyningRepository;

    @Autowired
    DyningImageRepository dyningImageRepository;

    @Autowired
    RoofDesignRepository roofDesignRepository;

    @Autowired
    RoofDesignInfoRepository roofDesignInfoRepository;

    @Test
    public void insertDyning() {

        IntStream.rangeClosed(1, 10).forEach(i -> {

            Member member = Member.builder().id("user" + i + "@icloud.com").build();
            
            // RoofDesignInfo roofDesignInfo = RoofDesignInfo.builder().build();
            // RoofDesignInfo roofDesignInfo = RoofDesignInfo.

            RoofDesignInfo roofDesignInfo = RoofDesignInfo.builder()
                    .rooftype((long) (Math.random()*5 +1))
                    .build();
                    
            RoofDesign roofDesign = RoofDesign.builder()
                    .roofdesigninfo(roofDesignInfo)
                    .build();

            roofDesignRepository.save(roofDesign);


            // RoofDesign roofDesign = RoofDesign.builder().roofdesigninfo(roofDesignInfo).build(); 
            
            Dyning dyning = Dyning.builder()
                    .dyningname("가게이름" + i)
                    .comment("사장님 한 마디" + i)
                    .location("실제가게위치" + i)
                    .foodtype( (long) (Math.random()*5 +1))
                    .businesshours("영업시간" + i)
                    .hashtag("해쉬태그" + i)
                    .ceoid(member)
                    .roofdesign(roofDesign)
                    .build();


            // roofDesignInfoRepository.save(roofDesignInfo);
            // roofDesignRepository.save(roofDesign);
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

    @Test
    public void testGetListDyning() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("dno").descending());

        Page<Object[]> result = dyningRepository.getListPage(pageable);

        System.out.println(result);

        result.get().forEach(row -> {
            Object[] arr = (Object[]) row;
            System.out.println(Arrays.toString(arr));
            System.out.println(arr[0]);
            System.out.println(arr[1]);
        });
    }
}