package ds.com.phoncnic.repository;


import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.CharacterLook;
import ds.com.phoncnic.entity.CharacterLookInfo;
import ds.com.phoncnic.entity.Member;
import lombok.extern.log4j.Log4j2;


@SpringBootTest
@Log4j2
public class CharacterLookRepositoryTests {
    @Autowired
    CharacterLookRepository repository;

    @Autowired
    private CharacterLookInfoRepository characterLookInforepository;

    @Autowired
    private MemberRepository memberRepository;

    //ChracterLookInfo 더미
    @Test
    public void insertChracterimg(){

        IntStream.rangeClosed(1, 3).forEach(i->{
            CharacterLookInfo characterLookinfo = CharacterLookInfo.builder()
            .hairname("hair"+i)
            .hairpath(UUID.randomUUID().toString())
            .clothesname("clothes"+i)
            .clothespath(UUID.randomUUID().toString())
            .build();
            characterLookInforepository.save(characterLookinfo);
        });
    }

    //ChracterLook 더미
    @Test
    public void insertDummies(){

        IntStream.rangeClosed(1, 10).forEach(i->{
            Long ch = ((long)(Math.random()*3+1));

            Member member = memberRepository.getMemberByMemberId("user"+i+"@icloud.com");
            log.info(i+"번째 멤버 생성"+member);
            CharacterLookInfo characterInfo = characterLookInforepository.getCharacterLookInfo(ch);
            log.info(i+"번째 멤버 인포 생성"+characterInfo);
            
            CharacterLook characterLook = CharacterLook.builder()
                .member(member)
                .characterLookinfo(characterInfo)
                .hairname("hair"+ch)
                .clothesname("clothes"+ch)
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