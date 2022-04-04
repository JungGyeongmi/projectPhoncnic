package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.Member;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    CharacterLookRepository repository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 10).forEach(
                i -> {
                    Member member = Member.builder()
                            .id("user" + i + "@icloud.com")
                            .nickname("user" + i)
                            .build();

                    memberRepository.save(member);
                }

        );

    }

  
}
