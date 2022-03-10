package ds.com.phoncnic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class CharacterLookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chno;

    private String hairname;
    private String hairpath;
    private String clothesname;
    private String clothespath;



    

}
