package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;

public interface DyningRepository extends JpaRepository<Dyning, Long> {

    // @Query("select d, di, r " +
    // "from Dyning d " +
    // "LEFT OUTER JOIN DyningImage di ON d = di.dyning "+
    // "LEFT OUTER JOIN RoofDesign r ON d = r.dyning "+
    // "group by d")
    // Page<Object[]> getListPage(Pageable pageable);

    //거리에서 가게명/루프패스
    @Query ("SELECT d, r FROM Dyning d LEFT JOIN RoofDesign r ON d.roofdesign = r.oono")
    List<Dyning> getStreetList();

    // @Query("SELECT d, r FROM Dyning d LEFT JOIN RoofDesign r ON d.roofdesign = r.oono where d.foodtype != 1L")
    // List<Object[]> getRestaurantList();

    // 특정 가게 상세페이지(dyning-Details)
    // @Query("select d, di from Dyning d " +
    //         "LEFT OUTER JOIN DyningImage di ON d = di.dyning where d.dno =:dno group by d")
    // List<Object[]> getDetailsPage(Long dno);

     @Query("select di from DyningImage di where di.dyning.id =:dno")
     List<DyningImage> getImageDetailsPage(Long dno);
}
