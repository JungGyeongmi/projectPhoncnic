package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.entity.Member;

@SpringBootTest
public class FollowRepositoryTests {

    @Autowired
    FollowRepository followRepository;

    @Test 
    
    public void insertFollowDummies(){
        IntStream.rangeClosed(1, 10).forEach(i->{
            int num = (int)(Math.random()*10+1);
            Follow follow = Follow.builder()
            .follower(Member.builder().id("user"+i+"@icloud.com").build())
            .artistname("user"+num)
            .dyningname("가게이름"+num)
            .build();

            followRepository.save(follow);
            

            
        });
    }

    @Test
    public void TestGetFollowList(){
        String id="user10@icloud.com";
        Follow follow = followRepository.getFollowList(id);
        System.out.println(follow);
    }

}
