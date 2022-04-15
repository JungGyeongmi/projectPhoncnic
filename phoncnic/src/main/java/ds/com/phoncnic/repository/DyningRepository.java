package ds.com.phoncnic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.repository.search.SearchDyningRepository;


public interface DyningRepository extends JpaRepository<Dyning, Long>, QuerydslPredicateExecutor<Dyning>, SearchDyningRepository {


    // 카페거리 리스트
    @Query("SELECT d, r FROM Dyning d LEFT JOIN RoofDesign r ON d.roofdesign = r.oono where d.foodtype = 1")
    List<Dyning> getCafeStreetList();

    // 음식점거리 리스트
    @Query("SELECT d, r FROM Dyning d LEFT JOIN RoofDesign r ON d.roofdesign = r.oono where d.foodtype != 1")
    List<Dyning> getRestaurantStreetList();

    @Modifying
    @Query("delete from Dyning d where d.ceoid.id=:id")
    void deleteByMemberId(String id);

    @Query("select d from Dyning d where d.ceoid.id=:id")
    List<Dyning> findByMemberId(String id);

    @Query("select d from Dyning d where d.ceoid.id=:id")
    Optional<Dyning> findDyningByMemberId(String id);

    @Query("select di from DyningImage di where di.dyning.id =:dno")
    List<DyningImage> getImageDetailsPage(Long dno);

    @Query("select d,count(e.eno) from Dyning d left join Emoji e on e.dyning.dno = dno where d.dno =:dno group by dno")
    List<Object[]> getDyningDetails(Long dno);


    @Modifying
    @Query("delete from Dyning d where d.dno=:dno")
    void deleteByDno(Long dno);

    // dyning 팔로워 count
    @Query("select count(f.dyningname) from Dyning d left join Follow f on f.dyningname = d.dyningname where d.dno =:dno group by dno")
    Long getDyningFollowerCount(Long dno);
}
