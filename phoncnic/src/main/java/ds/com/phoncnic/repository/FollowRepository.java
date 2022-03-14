package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    //
     @Query("select artistname from Follow f where f.follower.id =:id and artistname IS NOT NULL")
     List<Object> getfollowArtistList(String id);
     @Query("select dyningname from Follow f where f.follower.id =:id and dyningname IS NOT NULL")
     List<Object> getfollowDyningList(String id);

}
