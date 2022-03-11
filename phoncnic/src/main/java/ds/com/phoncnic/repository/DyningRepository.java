package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Dyning;

public interface DyningRepository extends JpaRepository <Dyning, Long> {

    // @Query("select d, di, r " +
    //         "from Dyning d " +
    //         "LEFT OUTER JOIN DyningImage di ON d = di.dyning "+
    //         "LEFT OUTER JOIN RoofDesign r ON d = r.dyning "+
    //         "group by d")
    // Page<Object[]> getListPage(Pageable pageable);
    
    //거리에서 루프디자인/가게명/루프패스
    @Query ("SELECT d, r FROM Dyning d LEFT JOIN RoofDesign r ON d.roofdesign = r.oono")
    List<Object[]> getStreetList(); 
    
    // @Query("SELECT d, r FROM Dyning d LEFT JOIN RoofDesign r ON d.roofdesign = r.oono where d.foodtype != 1L")
    // List<Object[]> getRestaurantList();

}
