package ds.com.phoncnic.repository;

import ds.com.phoncnic.entity.ApplicationForm;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long>{
 
  @Query("select af from ApplicationForm af where af.member.id=:id")  
  List<ApplicationForm> findByMemberId(String id);
 
  @Query("select af from ApplicationForm af where af.member.id=:id")  
  Optional<ApplicationForm> findApplicationFormByMemberId(String id);

  @Modifying
  @Query("delete from ApplicationForm af where af.member.id=:id")
  void deleteByApplicantId(String id);
}
