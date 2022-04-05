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
