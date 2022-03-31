package ds.com.phoncnic.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ds.com.phoncnic.dto.DyningImageDTO;
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
    private String locationdetails;
    private Long foodtype;
    private String businesshours;
    private String tel;

    private String hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member ceoid;

    @OneToMany(mappedBy = "dyning", fetch = FetchType.LAZY)
    List<DyningImage> image = new ArrayList<DyningImage>();

    @ManyToOne(fetch = FetchType.LAZY)
    private RoofDesign roofdesign;

    public void modifyDyning(String dyningname, String comment, String location,  String locationdetails, Long foodtype,
    String businesshours,String tel,String hashtag,RoofDesign roofdesign) {
        this.dyningname = dyningname;
        this.comment = comment;
        this.location = location;
        this.locationdetails =locationdetails;
        this.foodtype = foodtype;
        this.businesshours = businesshours;
        this.tel = tel;
        this.hashtag = hashtag;
        this.roofdesign = roofdesign;
    }
}