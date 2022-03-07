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

        IntStream.rangeClosed(1, 30).forEach(i->{

            int rand = (int)(Math.random()*10)+1;
            
            Dyning dyning = Dyning.builder().dno(rand).build();
            Gallery gallery = Gallery.builder().gno((long)rand).build();
            Member member = Member.builder().id("user"+rand+"iclooud.com").build();
            
            rand = (int)(Math.random()*10)+1;

            Follow follow = Follow.builder()
                // .followid("user"+rand+"@iclouc.com")
                .dyning(dyning)
                .gallery(gallery)
                .follower(member)
            .build();

            followRepository.save(follow);
        }); 
    }

}
