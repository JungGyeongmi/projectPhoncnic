package ds.com.phoncnic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Dyning;

public interface DyningRepository extends JpaRepository <Dyning, Long> {

    @Query("select d, di, r " +
            "from Dyning d " +
            "LEFT OUTER JOIN DyningImage di ON d = di.dyning "+
            "LEFT OUTER JOIN RoofDesign r ON d = r.dyning "+
            "group by d")
    Page<Object[]> getListPage(Pageable pageable);
 

    // @Query("select d, di " +
    //         "from Dyning d " +
    //         "LEFT OUTER JOIN DyningImage di ON d = di.dyning ")
    // Page<Object[]> getListPage(Pageable pageable);

}
