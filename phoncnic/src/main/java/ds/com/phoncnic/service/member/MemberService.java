package ds.com.phoncnic.service.member;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.AuthorityRole;
import ds.com.phoncnic.entity.CharacterLook;
import ds.com.phoncnic.entity.Member;

public interface MemberService {
  void updateMemberDTO(MemberDTO memberDTO);
  void modify(MemberDTO memberDTO);
  void remove(String id);
  MemberDTO getMember(String id);

  default Map<String, Object> dtoToEntity(MemberDTO memberDTO) {
    Map<String, Object> entityMap = new HashMap<>();

    Member member = Member.builder()
        .id(memberDTO.getId())
        .password(memberDTO.getPassword())
        .nickname(memberDTO.getNickname())
        .roleSet(memberDTO.getRoleSet().stream().map(
            new Function<String, AuthorityRole>() {
              @Override
              public AuthorityRole apply(String t) {
                if (t.equals("ROLE_USER"))
                  return AuthorityRole.USER;
                else if (t.equals("ROLE_CEO"))
                  return AuthorityRole.CEO;
                else if (t.equals("ROLE_ARTIST"))
                  return AuthorityRole.ARTIST;
                else if (t.equals("ROLE_ADMIN"))
                  return AuthorityRole.ADMIN;
                else
                  return AuthorityRole.USER;
              }
            }).collect(Collectors.toSet()))
        .build();

        entityMap.put("member", member);

          String id = memberDTO.getId();
          CharacterLook characterLook = CharacterLook.builder()
          .hairname("hair1")
          .clothesname("clothes1")
          .member(Member.builder().id(id).build())
          .build();

          entityMap.put("characterLook", characterLook);
      
    return entityMap;
  }

  default MemberDTO entityToDTO(Member member) {
    MemberDTO memberDTO = MemberDTO.builder()
        .id(member.getId())
        .password(member.getPassword())
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
