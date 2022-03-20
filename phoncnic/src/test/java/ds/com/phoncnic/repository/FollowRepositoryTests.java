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
    @Autowired
    MemberRepository memberRepository;
    //follow 테이블 삭제 -> Artist&Dyning 더미 돌리기

    //artist    팔로우 더미
    @Test
    public void insertArtistFollowDummies(){
        IntStream.rangeClosed(1, 10).forEach(i->{
            // memberRepository.findById("user"+i+"@icloud.com").get()
            Member member = Member.builder().id("user"+i+"@icloud.com").build();
            Follow follow = Follow.builder()
                    .follower(member)
                    .artistname("user" + i)
                    .build();
            followRepository.save(follow);
        });
    }

     //dyning 팔로우 더미
     @Test
     public void insertDyningFollowDummies(){
         IntStream.rangeClosed(1, 10).forEach(i->{ 
             Follow follow = Follow.builder()
                     .follower(Member.builder().id("user" + 1 + "@icloud.com").build())
                     .dyningname("가게이름" + i)
                     .build();
             followRepository.save(follow);
         });
     }
}
