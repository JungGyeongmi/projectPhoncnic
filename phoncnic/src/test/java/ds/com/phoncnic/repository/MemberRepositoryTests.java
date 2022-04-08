package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.AuthorityRole;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.service.member.MemberService;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    CharacterLookRepository repository;

    @Autowired
    MemberRepository memberRepository;
 
    @Autowired
    MemberService memberService;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 30).forEach(
            i -> {
                Member member = Member.builder()
                    .id("test" + i + "@gmail.com")
                    .nickname("test" + i)
                    .build();

                    member.addMemberRole(AuthorityRole.USER);
                    
                    if(i > 10) {
                        member.addMemberRole(AuthorityRole.ARTIST);
                    }

                    if(i > 20) {
                        member.addMemberRole(AuthorityRole.CEO);
                    }

                memberRepository.save(member);
            }
        );
    }

    @Test
    public void findByUserNickName() {
        Boolean test = memberRepository.findByMemberNickName("간지짱");
        log.info(test);
    }

    @Transactional
    @Test
    public void findMemberById() {
        String id = "gm950715@gmail.com";
        MemberDTO memberDTO = memberService.getMember(id);
        System.out.println(memberDTO);
    }

}
