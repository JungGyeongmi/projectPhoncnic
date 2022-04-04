package ds.com.phoncnic.repository;

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
            "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_f304c504-cf9a-4775-8f8f-6c2ecb86721a_hair1.png",
            "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_113e7563-4b0e-4226-a846-8ec53e3b70b5_hair2.png",
            "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_808b7166-dbe5-4b32-96a5-d94a91643157_hair3.png"
        };
        String[] clothe = {
            "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_d0aa38aa-f701-4417-b30d-ef327cdc3cd0_clothes1.png",
            "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_4a288317-4408-450b-a029-5aea40bd019e_clothes2.png",
            "/phoncnic/display?fileName=2022%5C04%5C01%2Fs_d574f3d1-f89d-4863-bd82-45f369e94924_clothes3.png"
        };

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
