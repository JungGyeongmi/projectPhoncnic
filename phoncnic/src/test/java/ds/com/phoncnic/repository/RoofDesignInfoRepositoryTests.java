package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.RoofDesignInfo;

@SpringBootTest
public class RoofDesignInfoRepositoryTests {

    @Autowired
    RoofDesignInfoRepository repository;

    @Test
    public void insertRoofInfo() {

        IntStream.rangeClosed(1, 5).forEach(i -> {
            RoofDesignInfo roofDesignInfo = RoofDesignInfo.builder()
            .roofpath(i+"roofpath.jpg")
            .kindofroof("kindofroop" + i)
            .build();
    
        repository.save(roofDesignInfo); 
        });
    }
}
