package ds.com.phoncnic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Dyning;

public interface DyningRepository extends JpaRepository <Dyning, Long> {

    @Query("select di, d from Dyning di LEFT OUTER JOIN DyningImage d ON di = d.dyning")
    Page<Object[]> getListPage(Pageable pageable);
 
}
