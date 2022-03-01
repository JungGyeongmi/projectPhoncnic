package ds.com.phoncnic.service;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.CharacterLook;
import ds.com.phoncnic.entity.Member;

public interface MemberService {
    
    MemberDTO getMyPage(String id);
    void modify(MemberDTO dto);


    default MemberDTO entityToDTO(Member member) {
        
        MemberDTO memberDTO = MemberDTO.builder()
            .id(member.getId())
            .nickname(member.getNickname())
            .password(member.getPassword())
            .moddate(member.getModDate())
            .regdate(member.getRegDate())
            .build();

            return memberDTO;
        }


    default Member dtoToEntity(MemberDTO memberDTO) {
        Member member = Member.builder()
                .id(memberDTO.getId())
                .nickname(memberDTO.getNickname())
                .password(memberDTO.getPassword())
                .build();
        return member;
    }


    default MemberDTO entitiesToMyPageDTO(Member member, CharacterLook characterLook){
        MemberDTO memberDTO = MemberDTO.builder()
            .id(member.getId())
            .nickname(member.getNickname())
            .password(member.getPassword())
            .regdate(member.getRegDate())
            .moddate(member.getModDate())
            .hair(characterLook.getHair())
            .clothes(characterLook.getClothes())
        .build();

        return memberDTO;
    } 
}
