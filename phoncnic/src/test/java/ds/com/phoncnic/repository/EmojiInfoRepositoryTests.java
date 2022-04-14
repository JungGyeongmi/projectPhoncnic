package ds.com.phoncnic.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.service.emoji.EmojiInfoService;

@SpringBootTest
public class EmojiInfoRepositoryTests {

    @Autowired
    EmojiInfoRepository emojiInfoRepository;

    @Autowired
    EmojiInfoService emojiInfoService;

    @Test
    public void insertDummise() {

        IntStream.rangeClosed(1, 5).forEach(i -> {
            String[] emojiInfoUrlArrays = {
                "/phoncnic/display?fileName=2022%5C04%5C01%2Fd2343817-9931-440f-abbd-765f2f55ca4c_shock.png",
                "/phoncnic/display?fileName=2022%5C04%5C01%2F6ba9d059-b6cf-490e-8492-03b535f79ac9_heart.png",
                "/phoncnic/display?fileName=2022%5C04%5C01%2Ffe161d7b-9b9c-43c3-919e-a988f66de69d_love.png",
                "/phoncnic/display?fileName=2022%5C04%5C01%2Fec3610a5-9dbe-473f-99eb-2ee74add8e7f_cryingsmile.png",
                "/phoncnic/display?fileName=2022%5C04%5C01%2F4b3998e6-0e5c-4e52-9535-d6ec85262e94_good.png"

                //     맥용
                // "/phoncnic/display?fileName=2022%2F04%2F05%2Fs_a8d9d8eb-529c-4d72-8682-fe5316c04844_shock.png",
                // "/phoncnic/display?fileName=2022%2F04%2F05%2Fs_da149937-bea9-4b43-8ab6-b8f9e3fe7c6c_heart.png",
                // "/phoncnic/display?fileName=2022%2F04%2F05%2Fs_949f2b1b-3fd1-4f4c-bc3a-cb9429a745a6_love.png",
                // "/phoncnic/display?fileName=2022%2F04%2F05%2Fs_c4736971-f732-4c24-8960-ea7d826a8240_cryingsmile.png",
                // "/phoncnic/display?fileName=2022%2F04%2F05%2Fs_1103e357-bdff-4052-b073-23c5a7e87564_good.png",
            };
            EmojiInfo emojiInfo = EmojiInfo.builder()
                .emojitype(i+"")
                .emojipath(emojiInfoUrlArrays[i-1])
                .kindofemoji("emoji"+i)
            .build();

            emojiInfoRepository.save(emojiInfo);

        });
    }

    @Test
    public void getEmojiInfoListTest() {

        List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();
        emojiInfoList.stream().forEach(System.out::println);

    }
}
