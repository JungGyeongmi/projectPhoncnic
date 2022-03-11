package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;

@SpringBootTest
public class FollowRepositoryTests {

    @Autowired
    FollowRepository followRepository;

    @Transactional
    @Test
    public void insertDummies() {

        IntStream.rangeClosed(1, 20).forEach(i -> {

            Dyning dyning = Dyning.builder()
                    .ceoid(Member.builder().id("user" + (Math.random() * 10) + 1 + "icloud.com").build())
                    .build();

            Gallery gallery = Gallery.builder()
                    .artistid(Member.builder().id("user" + (Math.random() * 10) + 1 + "icloud.com").build())
                    .build();

            Member member = Member.builder().id("user" + (Math.random() * 10) + 1 + "icloud.com").build();

            Follow follow = Follow.builder()
                    .myid(member)
                    .ceoid(dyning)
                    .artistid(gallery)
                    .build();

            followRepository.save(follow);
        });

    }

}
