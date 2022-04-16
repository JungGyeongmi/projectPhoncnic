package ds.com.phoncnic.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.ApplicationImage;

public interface ApplicationImageRepository extends JpaRepository<ApplicationImage, Long> {
  
  @Modifying
  @Query("delete from ApplicationImage api where api.applicationform.afno=:afno")
  void deleteByAfno(Long afno);
}
