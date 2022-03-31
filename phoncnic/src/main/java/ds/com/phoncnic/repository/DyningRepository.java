package ds.com.phoncnic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.repository.search.SearchDyningRepository;

public interface DyningRepository extends JpaRepository<Dyning, Long>, SearchDyningRepository {

    // 거리에서 가게명/루프패스
    @Query("SELECT d, r FROM Dyning d LEFT JOIN RoofDesign r ON d.roofdesign = r.oono")
    List<Dyning> getStreetList();

    @Modifying
    @Query("delete from Dyning d where d.ceoid.id=:id")
    void deleteByMemberId(String id);

    @Query("select d from Dyning d where d.ceoid.id=:id")
    List<Dyning> findByMemberId(String id);

    @Query("select d from Dyning d where d.ceoid.id=:id")
    Optional<Dyning> findDyningByMemberId(String id);

    @Query("select di from DyningImage di where di.dyning.id =:dno")
    List<DyningImage> getImageDetailsPage(Long dno);

    @Query ("select d,count(e.eno) from Dyning d left join Emoji e on e.dyning.dno = dno where d.dno =:dno group by dno")
    List<Object[]> getDyningDetails(Long dno);

    // 페이징 처리
    @Query("select d from Dyning d where foodtype != 1 ")
    Page<Dyning> getRestayrantPage(Pageable pageable);

    // 페이징 처리
    @Query("select d from Dyning d where foodtype = 1 ")
    Page<Dyning> getCafePage(Pageable pageable);


    // @Query("")

    // 특정 가게 상세페이지(dyning-Details)
    // @Query("select d, di from Dyning d " +
    // "LEFT OUTER JOIN DyningImage di ON d = di.dyning where d.dno =:dno group by
    // d")
    // List<Object[]> getDetailsPage(Long dno);


}
