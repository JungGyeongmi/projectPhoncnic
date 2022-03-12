package ds.com.phoncnic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Qna;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    @Query("select q from Qna q where answerstatus = true or mod(qno, 2) = 1 ")
    Page<Qna> getListPage(Pageable pageable);
    
}
