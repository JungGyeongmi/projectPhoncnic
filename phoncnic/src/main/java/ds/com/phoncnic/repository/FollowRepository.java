package ds.com.phoncnic.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.entity.Member;

public interface FollowRepository extends JpaRepository<Follow, Long> {

public interface FollowRepository  extends JpaRepository<Follow,Long> {
    // @Query("select f from Follow f where f.follower.id =:id")
    //  List<Object[]> getFollowList(String id);

     @Query("select artistname from Follow f where f.follower.id =:id")
     List getartistnameList(String id);
     @Query("select dyningname from Follow f where f.follower.id =:id")
     List getdyningnameList(String id);


}
