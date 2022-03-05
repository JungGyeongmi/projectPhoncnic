package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.RoofDesign;
import ds.com.phoncnic.entity.RoofDesignInfo;

@SpringBootTest
public class RoofDesignRepositoryTests {

    @Autowired
    RoofDesignRepository repository;

    @Test
    public void insertRoof() {
        IntStream.rangeClosed(1, 10).forEach(i -> {

             RoofDesignInfo roofDesignInfo = RoofDesignInfo.builder().rooftype((long) (Math.random()*5 +1)).build();
            RoofDesign roofDesign = RoofDesign.builder()
                    .roofdesigninfo(roofDesignInfo)
                    .build();

            repository.save(roofDesign);
        });

    }
}
