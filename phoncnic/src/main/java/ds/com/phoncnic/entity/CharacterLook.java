package ds.com.phoncnic.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@ToString (exclude = {"member", "characterLookinfo"})
public class CharacterLook extends BaseEntity{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long lno;

    private String hairname;
    private String clothesname;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private CharacterLookInfo characterLookinfo;

    
    public void changeHairname(String hairname) {
        this.hairname = hairname;
    }

    public void changeClothesname(String clothesname) {
        this.clothesname = clothesname;
    }
}
