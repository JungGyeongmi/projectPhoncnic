package ds.com.phoncnic.repository;

<<<<<<< HEAD
import org.springframework.boot.test.context.SpringBootTest;

=======
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.entity.Member;
>>>>>>> T_main

@SpringBootTest
public class FollowRepositoryTests {

<<<<<<< HEAD
=======
    @Autowired
    FollowRepository followRepository;

    // follow 테이블 삭제 -> Artist&Dyning 더미 돌리기
    // !!!!!!!!
    // !!!!!!!!
    // artist 팔로우 더미
    @Test
    public void insertArtistFollowDummies() {
        IntStream.rangeClosed(1, 10).forEach(i -> {

            List<Integer> randmember = new ArrayList<>();

            while (randmember.size() != 10) {
                int inputrandomNumber = (int) (Math.random() * 10) + 1;
                for (int k = 0; k < 10; k++) {
                    if (!randmember.contains(inputrandomNumber)) {
                        randmember.add(inputrandomNumber);
                        break;
                    }
                }
            }

            int ran = (int) (Math.random() * 10) + 1;
            for (int j = 0; j < (int) (Math.random() * 5) + 1; j++) {
                Follow follow = Follow.builder()
                        .follower(Member.builder().id("user" + randmember.get(j) + "@icloud.com").build())
                        .artistname("user" + ran)
                        .build();

                followRepository.save(follow);
            }
        });

    }

    // dyning 팔로우 더미
    @Test
    public void insertDyningFollowDummies() {
        IntStream.rangeClosed(1, 10).forEach(i -> {

            List<Integer> randmember = new ArrayList<>();

            while (randmember.size() != 10) {
                int inputrandomNumber = (int) (Math.random() * 10) + 1;
                for (int k = 0; k < 10; k++) {
                    if (!randmember.contains(inputrandomNumber)) {
                        randmember.add(inputrandomNumber);
                        break;
                    }
                }
            }

            int ran = (int) (Math.random() * 10) + 1;
            for (int j = 0; j < (int) (Math.random() * 5) + 1; j++) {
                Follow follow = Follow.builder()
                        .follower(Member.builder().id("user" + randmember.get(j) + "@icloud.com").build())
                        .dyningname("가게이름" + ran)
                        .build();

                followRepository.save(follow);
            }
        });
    }

    // @Test
    // public void TestGetFollowList(){
    // String id="user1@icloud.com";
    // List<Object[]> result = followRepository.getfollowArtistList2(id);
    // result.forEach(i->{System.out.println(i);});
    // }

>>>>>>> T_main
}
