package ds.com.phoncnic.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = { "dyning", "roofdesigninfo" })
public class RoofDesign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long oono;

    private String roofdesigntype;

    @OneToOne(mappedBy = "roofdesign", cascade = CascadeType.ALL)
    private Dyning dyning;

    @OneToOne
    @JoinColumn(name = "roofdesignInfo_rooftype", referencedColumnName = "rooftype")
    private RoofDesignInfo roofdesigninfo;

}