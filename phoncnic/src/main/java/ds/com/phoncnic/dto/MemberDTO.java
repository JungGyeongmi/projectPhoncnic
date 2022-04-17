package ds.com.phoncnic.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private String id;
    private String nickname;
    
    private LocalDateTime regdate, moddate;
    
    @Builder.Default
    private List<String> roleSet = new ArrayList<>();

    public void setRoleSet(Collection<GrantedAuthority> authorities) {
        List<String> a = authorities.stream().map(auth -> {
            String h = auth.toString();
            return h;
        }).collect(Collectors.toList());
        this.roleSet = a;
    }

    public void setRoleSet(List<String> roleSet) {
        this.roleSet = roleSet;
    }
}
