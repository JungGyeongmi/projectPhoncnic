package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.CharacterLookInfo;

public interface CharacterLookInfoRepository extends JpaRepository<CharacterLookInfo, Long> {

    // 입력된 member id의 세트 정보를 가져옴
    @Query("select ci from CharacterLookInfo ci left join CharacterLook c on c.setname=ci.setname where c.member.id =:id")
    CharacterLookInfo getSet(String id);

    @Query("SELECT ci FROM CharacterLookInfo ci WHERE ci.chno = :chno")
    CharacterLookInfo getDefaultCharacterLookByChno(Long chno);
}
