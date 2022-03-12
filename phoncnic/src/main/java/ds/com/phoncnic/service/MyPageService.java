package ds.com.phoncnic.service;

import ds.com.phoncnic.dto.MyPageDTO;
import ds.com.phoncnic.entity.CharacterLookInfo;
import ds.com.phoncnic.entity.Member;

public interface MyPageService {
    
    MyPageDTO getMyPage(String id);
    // void modify(MemberDTO dto);
    // void modify(MyPageDTO myPageDTO);


    default Member dtoToEntity(MyPageDTO mypageDTO) {
        Member member = Member.builder()
                .id(mypageDTO.getId())
                .nickname(mypageDTO.getNickname())
                .password(mypageDTO.getPassword())
                .build();
        return member;

    }

    //엔티티들을 취합해서 MyPageDTO로 변환
    default MyPageDTO entitiesToMyPageDTO(Member member, CharacterLookInfo characterLookInfo){
        MyPageDTO memberDTO = MyPageDTO.builder()
        .id(member.getId())
        .nickname(member.getNickname())
        .password(member.getPassword())
        .regdate(member.getRegDate())
        .moddate(member.getModDate())
        .hairname(characterLookInfo.getHairname())
        .hairpath(characterLookInfo.getHairpath())
        .clothesname(characterLookInfo.getClothesname())
        .clothespath(characterLookInfo.getClothespath())
        .build();
        return memberDTO;
    }


    
}
        
