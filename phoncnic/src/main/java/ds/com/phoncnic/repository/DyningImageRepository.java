package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.DyningImage;

public interface DyningImageRepository extends JpaRepository<DyningImage, Long>{
    
    @Modifying
    @Query("delete from DyningImage di where di.dyning.dno=:dno")
    void deleteByDno(Long dno);

}
