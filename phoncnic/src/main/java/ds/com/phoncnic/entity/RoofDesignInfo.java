package ds.com.phoncnic.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roofdesign")
public class RoofDesignInfo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rooftype;

    private String roofpath;
    private String kindofroof; 

    @OneToOne(mappedBy = "roofdesigninfo")
    private RoofDesign roofdesign;
}

