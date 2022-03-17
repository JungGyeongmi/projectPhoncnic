package ds.com.phoncnic.repository;

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
    public void insertFollowDummies2(){
        IntStream.rangeClosed(1, 1).forEach(i->{
            
            int num = (int)(Math.random()*10+1);

            Follow follow = Follow.builder()
            .follower(Member.builder().id("user"+1+"@icloud.com").build())
            .artistname("user"+num)
            .dyningname("가게이름"+num)
            .build();

            followRepository.save(follow);
        });
    }

    @Test
    public void insertFollowDummies3(){
        IntStream.rangeClosed(1, 1).forEach(i->{
            
            int num = (int)(Math.random()*10+1);

            Follow follow = Follow.builder()
            .follower(Member.builder().id("user"+1+"@icloud.com").build())
            .artistname("user"+num)
            .build();

            followRepository.save(follow);
            

            
        });
    }


    @Test
    public void TestGetFollowList(){
        String id="user1@icloud.com";
       List<Object> result1 =followRepository.getartistnameList(id);
       List<Object> result2 =followRepository.getdyningnameList(id);
        System.out.println(result1);
        System.out.println(result2);

        // for(Object a: result)System.out.println(a);
        
    //    System.out.println(Arrays.toString(arr));
    }

}
