package ds.com.phoncnic.repository;

import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.CharacterLook;
import ds.com.phoncnic.entity.CharacterLookInfo;
import ds.com.phoncnic.entity.Member;

@SpringBootTest
public class CharacterLookRepositoryTests {
    @Autowired
    CharacterLookRepository repository;

    @Autowired
    private CharacterLookInfoRepository characterLookInforepository;

    // ChracterLookInfo 더미
    @Test
    public void insertChracterimg() {
        String[] hair = {
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_31be520a-0677-4a36-bd06-3d4302c1408e_plainhair.jpeg",
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_24573630-8af9-4daa-b952-a3e6c6e17fc8_sporthair.jpeg",
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_69f9d028-e3a2-4edb-8022-c727870a6d92_safarihair.jpeg"
        };
        String[] clothe = {
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_f989f5af-33ac-4e0d-8350-61c80553140a_plainclothes.jpeg",
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_c0eba8ad-0c65-4a11-a503-933e53ea3393_sportclothes.jpeg",
            "/phoncnic/display?fileName=2022%5C03%5C30%2Fs_1c001188-ba04-4ffc-84cf-535c45060df0_safariclothes.jpeg"};

        IntStream.rangeClosed(1, 3).forEach(i -> {
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder()
                    .hairname("hair" + i)
                    .hairpath(hair[i-1])
                    .clothesname("clothes" + i)
                    .clothespath(clothe[i-1])
                    .build();
            characterLookInforepository.save(characterLookinfo);
        });
    }

    // ChracterLook 더미
    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 10).forEach(i->{

            Member member = Member.builder().id("user"+i+"@icloud.com").build();
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder().chno(1L).build();

            CharacterLook characterLook = CharacterLook.builder()
            .member(member)
            .characterLookinfo(characterLookinfo)
            .hairname("hair"+1)
            .clothesname("clothes"+1)
            .build();
            repository.save(characterLook);

        });
    }

    @Test
    public void TestGetCharacterLook() {

        CharacterLookInfo result = characterLookInforepository.getHair("user1@icloud.com");
        System.out.println(result.getHairname());
        System.out.println(result.getHairpath());
        CharacterLookInfo result2 = characterLookInforepository.getClothes("user1@icloud.com");
        System.out.println(result2.getClothesname());
        System.out.println(result2.getClothespath());

    }
}
