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
        String[] set = {
            "/phoncnic/display?fileName=2022%5C04%5C01%2F05471144-94c6-4f3f-a076-3fe99ba44b0c_set1.png",
            "/phoncnic/display?fileName=2022%5C04%5C01%2F9b6e64c8-4317-4256-8e28-b86efb541e3f_set2.png",
            "/phoncnic/display?fileName=2022%5C04%5C01%2Ff262f865-a15d-4274-9c8c-0b392c23f8ec_set3.png"
        };

        IntStream.rangeClosed(1, 3).forEach(i -> {
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder()                    
                    .setname("set" + i)
                    .setpath(set[i - 1])
                    .build();
            characterLookInforepository.save(characterLookinfo);
        });
    }

    // ChracterLook 더미
    @Test
     public void insertDummies() {

        IntStream.rangeClosed(1, 10).forEach(i -> {

            Member member = Member.builder().id("user" + i + "@icloud.com").build();
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder().chno(1L).build();

            CharacterLook characterLook = CharacterLook.builder()
                    .member(member)
                    .characterLookinfo(characterLookinfo)
                    .setname("set" + 1)
                    .build();

            repository.save(characterLook);
        });
    }


}
