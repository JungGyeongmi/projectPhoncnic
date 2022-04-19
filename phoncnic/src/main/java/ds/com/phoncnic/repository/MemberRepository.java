package ds.com.phoncnic.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.search.SearchMemberRepository;


public interface MemberRepository extends JpaRepository<Member, String>, SearchMemberRepository {
    
    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.id = :id")
    Boolean getMemberByMemberId(String id);

    @Query("select COUNT(m) > 0 from Member m where m.nickname = :nickname")
    Boolean findByMemberNickName(String nickname);

    @EntityGraph(attributePaths = {"roleSet"}, type= EntityGraphType.LOAD)
    @Query("select m from Member m where id= :id")
    Optional<Member> findByEmail(String id);

    @Query("select nickname from Member where id = :id")
    String getNicknameById(String id);

    @Query("select m from Member m")
    Page<Member> getMemberPage(Pageable pageable);
   
}
