package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.service.MemberService;
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

        IntStream.rangeClosed(1, 10).forEach(
            i -> {
                Member member = Member.builder()
                    .id("user" + i + "@icloud.com")
                    .nickname("user" + i)
                    .password("1234")
                    .build();

                memberRepository.save(member);
            }

        );

    }

    @Test
    public void getMypageData() {

        Object result = repository.getMypageData("user1@icloud.com");
        Object[] arr = (Object[]) result;
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }

    @Test
    public void getMypageDTO() {

        MemberDTO memberDTO = memberService.getMyPage("user1@icloud.com");

        log.info(memberDTO);

    }
}
