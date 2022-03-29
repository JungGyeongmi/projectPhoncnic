package ds.com.phoncnic.security.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User {
  String id;
  String password;
  String nickname;
  private Map<String, Object> attr;
  // Social에서 오는 OAuth정보

  // DB로부터 사용자를 초기화하는 생성자1
  public AuthMemberDTO(String id, String password, String nickname,
      Collection<? extends GrantedAuthority> authorities) {
    super(id, password, authorities);
    this.id = id;
    this.nickname = nickname;
    this.password = password;
    log.info("ClubAuthMemberDTO 생성자 실행");
  }

  // OAuth로부터 사용자를 초기화하는 생성자2
  public AuthMemberDTO(String id, String password, String nickname, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
    this(id, nickname, password, authorities);
    this.attr = attr;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return this.attr;
  }

  @Override
  public String getName() {
    return null;
  }
  
}
