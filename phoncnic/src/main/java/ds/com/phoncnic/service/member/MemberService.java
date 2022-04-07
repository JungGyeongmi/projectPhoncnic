package ds.com.phoncnic.service.member;

import java.util.function.Function;
import java.util.stream.Collectors;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.AuthorityRole;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.security.dto.AuthMemberDTO;

public interface MemberService {
  void updateMemberDTO(MemberDTO memberDTO);
  void modify2(AuthMemberDTO dto);
  void remove(String id);
  MemberDTO getMember(String id);

  default Member dtoToEntity(MemberDTO memberDTO) {
    Member member = Member.builder()
        .id(memberDTO.getId())
        .nickname(memberDTO.getNickname())
        .roleSet(memberDTO.getRoleSet().stream().map(
            new Function<String, AuthorityRole>() {
              @Override
              public AuthorityRole apply(String t) {
                if (t.equals("[ROLE_USER]"))
                  return AuthorityRole.USER;
                else if (t.equals("[ROLE_CEO]"))
                  return AuthorityRole.CEO;
                else if (t.equals("[ROLE_ARTIST]"))
                  return AuthorityRole.ARTIST;
                else if (t.equals("[ROLE_ADMIN]"))
                  return AuthorityRole.ADMIN;
                else
                  return AuthorityRole.USER;
              }
            }).collect(Collectors.toSet()))
        .build();

      
    return member;
  }

  default MemberDTO entityToDTO(Member member) {
    MemberDTO memberDTO = MemberDTO.builder()
        .id(member.getId())
        .nickname(member.getNickname())
        .roleSet(member.getRoleSet().stream().map(
            role -> new String("ROLE_" + role.name()))
            .collect(Collectors.toList()))
        .regdate(member.getRegDate())
        .moddate(member.getModDate())
        .build();
    return memberDTO;
  }
}
