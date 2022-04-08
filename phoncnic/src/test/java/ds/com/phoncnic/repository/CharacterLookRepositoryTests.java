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
    MemberRepository memberRepository;

    @Autowired
    private CharacterLookInfoRepository characterLookInforepository;

    // ChracterLookInfo 더미
    @Test
    public void insertChracterimg() {
        String[] hair = {
            "/phoncnic/display?fileName=2022%2F03%2F31%2F08bb5eef-d682-47c4-9030-8727c5faab04_hair1.png",
            "/phoncnic/display?fileName=2022%2F03%2F31%2Fa7577cbf-aa00-4c44-92e2-8487b416cec0_hair2.png",
            "/phoncnic/display?fileName=2022%2F03%2F31%2F185fb82e-20b6-4375-8ded-bd7b56413c69_hair3.png"
        };
        String[] clothe = {
            "/phoncnic/display?fileName=2022%2F03%2F31%2Ff2837c1a-553a-4823-b9db-24a45c64e29a_clothes1.png",
            "/phoncnic/display?fileName=2022%2F03%2F31%2Fcff62990-63fc-4bfc-bea7-0e884bd650b3_clothes2.png",
            "/phoncnic/display?fileName=2022%2F03%2F31%2F3db2246e-c482-47dc-8835-765aa531b6e2_clothes3.png"
        };

        IntStream.rangeClosed(1, 3).forEach(i -> {
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder()
                    .hairname("hair" + i)
                    .hairpath(hair[i - 1])
                    .clothesname("clothes" + i)
                    .clothespath(clothe[i - 1])
                    .build();
            characterLookInforepository.save(characterLookinfo);
        });
    }

    // ChracterLook 더미
    @Test
     public void insertDummies() {

        IntStream.rangeClosed(1, 30).forEach(i -> {

            Member member = Member.builder().id("test" + i + "@gmail.com").build();
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder().chno(1L).build();

            CharacterLook characterLook = CharacterLook.builder()
                    .member(member)
                    .characterLookinfo(characterLookinfo)
                    .hairname("hair" + 1)
                    .clothesname("clothes" + 1)
                    .build();

            repository.save(characterLook);
        });
    }


}
