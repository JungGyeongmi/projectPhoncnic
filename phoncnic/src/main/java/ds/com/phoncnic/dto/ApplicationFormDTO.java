package ds.com.phoncnic.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationFormDTO {
  
  private Boolean applicationtype;
  private String content;
  
  private String dyningname;

  private String applicant;

  @Builder.Default
  private List<ApplicationImageDTO> businessregistrationDTO = new ArrayList<>();

}
