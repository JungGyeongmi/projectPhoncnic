package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.RoofDesign;

@SpringBootTest
public class RoofDesignRepositoryTests {

    @Autowired
    RoofDesignRepository repository;

    @Test
    public void insertRoof() {
        IntStream.rangeClosed(1, 5).forEach(i -> {

            RoofDesign roofDesign = RoofDesign.builder()
                    .roofname("루프" + i)
                    .rooftype((long) i)
                    .roofpath("roofpath" + i + ".jpg")
                    .build();

            repository.save(roofDesign);
        });

    }

//     @Test
//     public void insertRoofInfo() {

//         IntStream.rangeClosed(1, 5).forEach(i -> {
//             RoofDesignInfo roofDesignInfo = RoofDesignInfo.builder()
//             .roofpath(i+"roofpath.jpg")
//             .kindofroof("kindofroop" + i)
//             .build();
    
//         repository.save(roofDesignInfo); 
//         });
//     }
// }

    
}
