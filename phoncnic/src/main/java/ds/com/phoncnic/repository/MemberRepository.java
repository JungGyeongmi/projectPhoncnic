package ds.com.phoncnic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
    
    @Query("select m from Member m where m.id = :id")
    Member getMemberByMemberId(String id);

    @Query("select COUNT(m) > 0 from Member m where m.nickname = :nickname")
    Boolean findByMemberNickName(String nickname);

    /*
     EntityGraph
     LOAD: entity graph에 명시한 attribute는 EAGER로 패치하고, 
     나머지 attribute는 entity에 명시한 fetch type이나 
     디폴트 FetchType으로 패치
     FETCH: entity graph에 명시한 attribute는 EAGER로 패치하고, 나머지 attribute는 LAZY로 패치
     Query : 객체이기 때문에 ClubMember의 대소문자는 구분해줘야한다 
    */
    //EntityGraph일 경우
    //FETCH: entity graph에 명시한 attribute는 EAGER로 패치하고, 
    //나머지 attribute는 LAZY로 패치
    //LOAD: entity graph에 명시한 attribute는 EAGER로 패치하고, 
    //나머지 attribute는 entity에 명시한 fetch type이나 
    //디폴트 FetchType으로 패치
    @EntityGraph(attributePaths = {"roleSet"}, type= EntityGraphType.LOAD)
    @Query("select m from Member m where id= :id")
    Optional<Member> findByEmail(String id);
   
}
