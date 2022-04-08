package ds.com.phoncnic.security.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.json.simple.parser.ParseException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.api.HangulApi;
import ds.com.phoncnic.entity.AuthorityRole;
import ds.com.phoncnic.entity.CharacterLook;
import ds.com.phoncnic.entity.CharacterLookInfo;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.CharacterLookRepository;
import ds.com.phoncnic.repository.MemberRepository;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class OAuth2UserDetailsService extends DefaultOAuth2UserService {

  private final MemberRepository memberRepository;
  private final CharacterLookRepository characterLookRepository;
  // private final HangulApi hangul;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

    log.info("OAuth2UserDetailsService..userRequest:" + userRequest);

    String clientName = userRequest.getClientRegistration().getClientName();

    log.info("clientName:" + clientName);
    log.info("getParameters:" + userRequest.getAdditionalParameters());

    OAuth2User oAuth2User = super.loadUser(userRequest);

    log.info("===========================");

    oAuth2User.getAttributes().forEach((k, v) -> {
      log.info(k + ":" + v);
    });

    String email = null;

    if (clientName.equals("Google"))
      email = oAuth2User.getAttribute("email");

    log.info("email:" + email);

    Member member = saveSocialMember(email);

    AuthMemberDTO authMemberDTO = new AuthMemberDTO(
        member.getId(),
        member.getNickname(),
        member
            .getRoleSet().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
            .collect(Collectors.toList()),
        oAuth2User.getAttributes());

    return authMemberDTO;
  }

  private Member saveSocialMember(String email) {

    Optional<Member> result = memberRepository.findByEmail(email);

    if (result.isPresent())
      return result.get();
    log.info("returnfire");

    String nickName = randomNick() == null || randomNick() == "" ? UUID.randomUUID().toString() : randomNick();
    Member member = Member.builder()
        .id(email)
        .nickname(nickName)
        .build();

    member.addMemberRole(AuthorityRole.USER);
    memberRepository.save(member);
    CharacterLook characterLook = CharacterLook.builder()
        .hairname("hair1")
        .clothesname("clothes1")
        .characterLookinfo(CharacterLookInfo.builder().chno(43L).build())
        .member(Member.builder().id(email).build())
        .build();
    characterLookRepository.save(characterLook);

    return member;
  }

  public String randomNick() {
    String nickName = "";
    do {
      int random = (int) (Math.random() * 10);
      int idx = (int) (Math.random() * 3);
      try {
        // "keyword" , 17L 명사검색
        List<String> nickNameList = HangulApi.hangul(Integer.toString(random), 17L);
        // log.info(nickNameList);
        nickName = nickNameList.get(idx);
      } catch (IOException | ParseException e) {
        e.printStackTrace();
      }
    } while(memberRepository.findByMemberNickName(nickName));
    log.info("random한 nickName생성...." + nickName);
    return nickName;
  }
}