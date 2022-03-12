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

        IntStream.rangeClosed(1, 3).forEach(i -> {
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder()
                    .hairname("hair" + i)
                    .hairpath(UUID.randomUUID().toString())
                    .clothesname("clothes" + i)
                    .clothespath(UUID.randomUUID().toString())
                    .build();
            characterLookInforepository.save(characterLookinfo);
        });
    }

    // ChracterLook 더미
    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Long ch = ((long) (Math.random() * 3 + 1));

            Member member = Member.builder().id("user" + i + "@icloud.com").build();
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder().chno(ch).build();

            CharacterLook characterLook = CharacterLook.builder()
                    .member(member)
                    .characterLookinfo(characterLookinfo)
                    .hairname("hair" + ch)
                    .clothesname("clothes" + ch)
                    .build();
            repository.save(characterLook);

        });
    }

    // @Test
    // public void TestGetCharacterLook() {

    // CharacterLookInfo result =
    // characterLookInforepository.getCharacterImgs("user1@icloud.com");
    // System.out.println(result.getHairpath());
    // System.out.println(result.getClothespath());

    // }
}