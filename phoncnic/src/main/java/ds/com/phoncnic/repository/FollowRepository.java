package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Follow;

public interface FollowRepository  extends JpaRepository<Follow,Long> {
    // @Query("select f from Follow f where f.follower.id =:id")
    //  List<Object[]> getFollowList(String id);
     
     @Query("select artistname from Follow f where f.follower.id =:id and artistname IS NOT NULL")
     List<Object> getartistnameList(String id);
     @Query("select dyningname from Follow f where f.follower.id =:id and dyningname IS NOT NULL")
     List<Object> getdyningnameList(String id);

    
}
