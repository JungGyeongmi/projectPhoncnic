package ds.com.phoncnic.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roofdesign")
public class RoofDesignInfo {

    @Id
    private Long rooftype;

    private String roofpath;
    private String kindofroop;

    @OneToOne(mappedBy = "roofdesigninfo", cascade = CascadeType.ALL)
    private RoofDesign roofdesign;
}