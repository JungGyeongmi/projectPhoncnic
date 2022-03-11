package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Follow;

public interface FollowRepository  extends JpaRepository<Follow,Long> {
    @Query("select f from Follow f where f.follower.id =:id")
    Follow getFollowList(String id);
}
