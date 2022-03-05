package ds.com.phoncnic.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@ToString(exclude = { "ceoid", "image", "roofdesign" })
public class Dyning extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dno;

    private String dyningname;
    private String comment;
    
    private String location;
    private Long foodtype;
    private String businesshours;

    private String hashtag;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Member ceoid;

    @OneToMany(mappedBy = "dyning", fetch = FetchType.LAZY)
    List<DyningImage> image = new ArrayList<DyningImage>();

    @OneToOne
    @JoinColumn(name="roofdesign_oono", referencedColumnName = "oono")
    private RoofDesign roofdesign;




}