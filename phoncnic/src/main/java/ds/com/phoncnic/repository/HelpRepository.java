package ds.com.phoncnic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Help;

public interface HelpRepository extends JpaRepository<Help, Long> {

    @Query("select q from Help q where answerstatus = true ")
    Page<Help> getListPage(Pageable pageable);

}