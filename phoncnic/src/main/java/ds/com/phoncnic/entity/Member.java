package ds.com.phoncnic.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {

    @Id
    private String id;

    private String nickname;
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<AuthorityRole> roleSet = new HashSet<>();

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void addMemberRole(AuthorityRole authorityRole){
        roleSet.add(authorityRole);
    }

}