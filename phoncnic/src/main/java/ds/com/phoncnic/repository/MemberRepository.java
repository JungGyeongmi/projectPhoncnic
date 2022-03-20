package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
    
    @Query("select m from Member m where m.id = :id")
    Member getMemberByMemberId(String id);

}
