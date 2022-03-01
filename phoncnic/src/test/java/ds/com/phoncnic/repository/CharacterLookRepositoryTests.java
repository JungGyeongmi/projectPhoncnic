package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.CharacterLook;
import ds.com.phoncnic.entity.Member;

@SpringBootTest
public class CharacterLookRepositoryTests {
    @Autowired
    CharacterLookRepository repository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 10).forEach(
                i -> {
                    Member member = Member.builder()
                            .id("user" + i + "@icloud.com").build();

                    CharacterLook characterLook = CharacterLook.builder()
                            .hair(i)
                            .clothes(i)
                            .member(member)
                            .build();

                    repository.save(characterLook);
                }

        );

    }
}