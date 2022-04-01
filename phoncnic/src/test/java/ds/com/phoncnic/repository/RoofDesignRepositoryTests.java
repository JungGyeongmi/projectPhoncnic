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
                "/phoncnic/display?fileName=2022%5C03%5C30%2Fced3a714-a8cb-46f8-8e47-61f967a796ce_%EC%B9%B4%ED%8E%98.png",
                "/phoncnic/display?fileName=2022%5C03%5C30%2Fdd8a3ebf-4b79-4106-ab98-2e3817949e2f_%ED%95%9C%EC%8B%9D.png",
                "/phoncnic/display?fileName=2022%5C03%5C30%2F1ad92417-d2da-4348-9ff1-a2e76e437d8a_%EC%9D%BC%EC%8B%9D.png",
                "/phoncnic/display?fileName=2022%5C03%5C30%2F595a30eb-cc21-41d1-b255-ef4a0c6adfa1_%EC%A4%91%EC%8B%9D.png",
                "/phoncnic/display?fileName=2022%5C03%5C30%2Fdb584359-6f49-43fe-8b57-920f6c2cb9eb_%EC%96%91%EC%8B%9D.png"
            };
            String[] thumbnail = {
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_ced3a714-a8cb-46f8-8e47-61f967a796ce_%EC%B9%B4%ED%8E%98.png",
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_dd8a3ebf-4b79-4106-ab98-2e3817949e2f_%ED%95%9C%EC%8B%9D.png",
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_1ad92417-d2da-4348-9ff1-a2e76e437d8a_%EC%9D%BC%EC%8B%9D.png",
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_595a30eb-cc21-41d1-b255-ef4a0c6adfa1_%EC%A4%91%EC%8B%9D.png",
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_db584359-6f49-43fe-8b57-920f6c2cb9eb_%EC%96%91%EC%8B%9D.png"
            };
            String[] name = {"카페","한식","일식","중식","양식"};

            RoofDesign roofDesign = RoofDesign.builder()
                    .roofname(name[i-1])
                    .rooftype((long) i)
                    .roofpath(path[i-1])
                    .roofthumbnail(thumbnail[i-1])
                    .build();

            repository.save(roofDesign);
        });

    }
    
}
