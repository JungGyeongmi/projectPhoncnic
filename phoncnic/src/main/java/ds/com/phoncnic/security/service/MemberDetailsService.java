package ds.com.phoncnic.security.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.MemberRepository;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
  
  private final MemberRepository memberRepository;
  
  @Override
  public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
    
    log.info("BiUserDetailsService loadUserByUsername:"+username);
    Optional<Member> result= memberRepository.findByEmail(username);

    if(!result.isPresent()) {
      throw new UsernameNotFoundException("Check Social");
    }

    Member member = result.get();

    log.info("member : "+member);
    
    AuthMemberDTO authMemberDTO = new AuthMemberDTO(
      member.getId(),
      member.getNickname(),
      member.getRoleSet().stream().map(
        role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
              .collect(Collectors.toSet())
    );

    return authMemberDTO;
  }
}
