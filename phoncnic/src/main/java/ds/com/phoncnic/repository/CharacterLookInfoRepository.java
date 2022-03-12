package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.CharacterLookInfo;



public interface CharacterLookInfoRepository extends JpaRepository<CharacterLookInfo,Long>{

    //입력된 member id 의 캐릭터 룩 정보를 가져옴
    @Query("select ci from Member m left join CharacterLook c on c.member = m.id "+
    "left join CharacterLookInfo ci on ci.hairname = c.hairname "+
    "where m.id = :id")
    CharacterLookInfo getCharacterImgs(String id);
}
