package ds.com.phoncnic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationImageDTO {
  
  private String imagename;
  private String imagepath;
  private String uuid;

}
