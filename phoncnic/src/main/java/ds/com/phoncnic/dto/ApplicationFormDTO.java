package ds.com.phoncnic.dto;

import java.time.LocalDateTime;
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
  
  private Long afno;

  private Boolean applicationtype;
  private String content;
  
  private String dyningname;

  private String applicant;

  LocalDateTime regdate, moddate;

  @Builder.Default
  private List<ApplicationImageDTO> applicationImageDTOList = new ArrayList<>();

}
