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
            String[] path = {
                    "/phoncnic/display?fileName=2022%5C04%5C01%2Fab470580-40db-452f-b803-f5ec344e74ff_cafe.png",
                    "/phoncnic/display?fileName=2022%5C04%5C01%2Fc4e9e37d-b708-4ba2-974e-27e65f472b3c_korea.png",
                    "/phoncnic/display?fileName=2022%5C04%5C01%2F5017b721-3d27-4bdf-9146-11d01af4fd7a_japan.png",
                    "/phoncnic/display?fileName=2022%5C04%5C01%2F3bc447cf-dca1-48d1-842e-8f7c4b1c4b48_chinese.png",
                    "/phoncnic/display?fileName=2022%5C04%5C01%2F3bf71bdd-9583-45a3-84e4-682119476704_taco.png"
            };
            String[] thumbnail = {
                    "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_ab470580-40db-452f-b803-f5ec344e74ff_cafe.png",
                    "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_c4e9e37d-b708-4ba2-974e-27e65f472b3c_korea.png",
                    "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_5017b721-3d27-4bdf-9146-11d01af4fd7a_japan.png",
                    "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_3bc447cf-dca1-48d1-842e-8f7c4b1c4b48_chinese.png",
                    "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_3bf71bdd-9583-45a3-84e4-682119476704_taco.png"
            };
            String[] name = { "카페", "한식", "일식", "중식", "양식" };

            RoofDesign roofDesign = RoofDesign.builder()
                    .roofname(name[i - 1])
                    .rooftype((long) i)
                    .roofpath(path[i - 1])
                    .roofthumbnail(thumbnail[i - 1])
                    .build();

            repository.save(roofDesign);
        });

    }
}
