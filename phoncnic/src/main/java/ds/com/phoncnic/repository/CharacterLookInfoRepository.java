package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.CharacterLookInfo;



public interface CharacterLookInfoRepository extends JpaRepository<CharacterLookInfo,Long>{

    //입력된 member id 의 헤어 정보를 가져옴
    @Query("select ci from CharacterLookInfo ci left join CharacterLook c on c.hairname=ci.hairname where c.member.id =:id")
    CharacterLookInfo getHair(String id);
    //입력된 member id 의 옷 정보를 가져옴
    @Query("select ci from CharacterLookInfo ci left join CharacterLook c on c.clothesname=ci.clothesname where c.member.id =:id")
    CharacterLookInfo getClothes(String id);
}
