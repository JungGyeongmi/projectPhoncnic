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
@ToString (exclude = {"member"})
public class ApplicationForm extends BaseRegiDate {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long afno;

  private Boolean applicationtype;
  private String content;

  private String dyningname;

  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  @OneToMany(mappedBy = "applicationform", fetch = FetchType.LAZY)
  List<ApplicationImage> businessregistration = new ArrayList<ApplicationImage>();

}
