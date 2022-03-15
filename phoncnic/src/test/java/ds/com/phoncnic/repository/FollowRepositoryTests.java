package ds.com.phoncnic.repository;

import java.util.Arrays;
import java.util.List;
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
    //artist    팔로우 더미
    @Test
    public void insertArtistFollowDummies(){
        IntStream.rangeClosed(1, 10).forEach(i->{
            Follow follow = Follow.builder()
                    .follower(Member.builder().id("user" + i + "@icloud.com").build())
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
                     .follower(Member.builder().id("user" + i + "@icloud.com").build())
                     .dyningname("가게이름" + i)
                     .build();
 
             followRepository.save(follow);
         });
     }



    @Test
    public void insertFollowDummies3(){
        IntStream.rangeClosed(1, 1).forEach(i->{


            Follow follow = Follow.builder()
            .follower(Member.builder().id("user"+1+"@icloud.com").build())
            .artistname("user"+3)
            .build();

            followRepository.save(follow);


        });
    }

    // @Test
    // public void TestGetFollowList(){
    //     String id="user1@icloud.com";
    //    List<Object[]> result = followRepository.getfollowArtistList2(id);
    //   result.forEach(i->{System.out.println(i);});
    // }

}
