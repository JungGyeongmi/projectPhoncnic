package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.CharacterLook;

public interface CharacterLookRepository extends JpaRepository<CharacterLook,Long>{
    @Query("select m,ch from Member m left join CharacterLook ch on m = ch.member where m.id=:id")
    Object getMypageData(String id);
}
