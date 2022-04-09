package ds.com.phoncnic.repository;

import java.util.ArrayList;
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

    // follow 테이블 삭제 -> Artist&Dyning 더미 돌리기
    // artist 팔로우 더미
    @Test
    public void insertArtistFollowDummies() {
        IntStream.rangeClosed(1, 30).forEach(i -> {

            List<Integer> randmember = new ArrayList<>();

            while (randmember.size() != 30) {
                int inputrandomNumber = (int) (Math.random() * 30) + 1;
                for (int k = 0; k < 30; k++) {
                    if (!randmember.contains(inputrandomNumber)) {
                        randmember.add(inputrandomNumber);
                        break;
                    }
                }
            }

            for (int j = 0; j < (int) (Math.random() * 5) + 1; j++) {
                Follow follow = Follow.builder()
                        .follower(Member.builder().id("test" + randmember.get(j) + "@gmail.com").build())
                        .artistname("test" + i)
                        .build();

                followRepository.save(follow);
            }
        });

    }

    // dyning 팔로우 더미
    @Test
    public void insertDyningFollowDummies() {
        IntStream.rangeClosed(1, 30).forEach(i -> {

            List<Integer> randmember = new ArrayList<>();

            while (randmember.size() != 30) {
                int inputrandomNumber = (int) (Math.random() * 30) + 1;
                for (int k = 0; k < 30; k++) {
                    if (!randmember.contains(inputrandomNumber)) {
                        randmember.add(inputrandomNumber);
                        break;
                    }
                }
            }

            for (int j = 0; j < (int) (Math.random() * 5) + 1; j++) {
                Follow follow = Follow.builder()
                        .follower(Member.builder().id("test" + randmember.get(j) + "@gmail.com").build())
                        .dyningname("가게이름" + i)
                        .build();

                followRepository.save(follow);
            }
        });
    }
}
