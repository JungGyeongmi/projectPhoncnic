package ds.com.phoncnic.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@ToString(exclude = "dyning")
public class DyningImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dino;
    
    private String menuimagename;
    private String menuimageuuid;
    private String menuimagepath;
    
    private String backgroundname;
    private String backgrounduuid;
    private String backgroundpath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dyning_dno")
    private Dyning dyning;
}