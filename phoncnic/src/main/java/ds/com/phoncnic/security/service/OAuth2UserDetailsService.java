package ds.com.phoncnic.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.entity.AuthorityRole;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.MemberRepository;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class OAuth2UserDetailsService extends DefaultOAuth2UserService{
  
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    
    log.info("CommonOAuth2UserDetailsService..userRequest:"+userRequest);
    
    String clientName = userRequest.getClientRegistration().getClientName();
    
    log.info("clientName:"+clientName);
    log.info("getParameters:"+userRequest.getAdditionalParameters());

    OAuth2User oAuth2User = super.loadUser(userRequest);
    
    log.info("===========================");
   
    oAuth2User.getAttributes().forEach((k,v)->{
      log.info(k+":"+v);
    });

    String email = null;
    
    if(clientName.equals("Google"))
      email = oAuth2User.getAttribute("email");
                                                                        
      log.info("email:"+email);
    
      Member member = saveSocialMember(email);
    
    AuthMemberDTO authMemberDTO = new AuthMemberDTO(
      member.getId(), 
      member.getPassword(), 
      member.getNickname(), 
      member
         .getRoleSet().stream()
         .map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
         .collect(Collectors.toList()),
      oAuth2User.getAttributes()
    );

    return authMemberDTO;
  }
  
  private Member saveSocialMember(String email){
    
    Optional<Member> result = memberRepository.findByEmail(email);

    if(result.isPresent()) return result.get();

    // 여기서 random 
    Member member = Member.builder()
      .id(email)
      .nickname("빵형")
      .password(passwordEncoder.encode("1"))
    .build();

    member.addMemberRole(AuthorityRole.USER);
    memberRepository.save(member);

    return member;
  }
}
