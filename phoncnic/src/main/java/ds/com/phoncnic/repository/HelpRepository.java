package ds.com.phoncnic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Help;
import ds.com.phoncnic.repository.search.SearchHelpRepository;

public interface HelpRepository extends JpaRepository<Help, Long>, SearchHelpRepository {

    @Query("select q from Help q where answerstatus = true or mod(qno, 2) = 0 ")
    Page<Help> getListPage(Pageable pageable);
    
    @Modifying
    @Query("delete from Help h where h.writer.id=:id")
    void deleteByMemberId(String id);

}
