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
@ToString(exclude = "applicationform")
public class ApplicationImage {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long afino;
  
  private String imagename;
  private String imagepath;
  private String uuid;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "applicationform_afno")
  private ApplicationForm applicationform;
}
