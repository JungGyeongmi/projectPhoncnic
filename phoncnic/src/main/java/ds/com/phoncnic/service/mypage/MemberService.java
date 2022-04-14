package ds.com.phoncnic.service.mypage;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.Member;

public interface MemberService {
    
    MemberDTO getMember(String id);
    void modify(MemberDTO memberDTO);
    void remove(String id);


    default Member dtoToEntity(MemberDTO mypageDTO) {
        Member member = Member.builder()
                .id(mypageDTO.getId())
                .nickname(mypageDTO.getNickname())
                .build();
        return member;

    }

    default MemberDTO entityToDTO(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
        .id(member.getId())
        .nickname(member.getNickname())
        .regdate(member.getRegDate())
        .moddate(member.getModDate())
        .build();
        return memberDTO;
    }


    
}
        
