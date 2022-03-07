package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.com.phoncnic.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
    
}
