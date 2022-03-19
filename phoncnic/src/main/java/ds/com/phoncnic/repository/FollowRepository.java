package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    //아티스트 리스트
    @Query("select artistname from Follow f where f.follower.id =:id and artistname IS NOT NULL")
    List<Object> getfollowArtistList(String id);
     //팔로우 리스트
    @Query("select dyningname from Follow f where f.follower.id =:id and dyningname IS NOT NULL")
    List<Object> getfollowDyningList(String id);

    @Query("select f from Follow f where f.follower.id=:id and artistname = :name")
    Follow getFollownameArtist(String id, String name);

    @Query("select f from Follow f where f.follower.id=:id and dyningname = :name")
    Follow getFollownameDyning(String id, String name);
    
    @Modifying
    @Query("delete from Follow f where f.follower.id=:id")
    void deleteByMemberId(String id);
}
