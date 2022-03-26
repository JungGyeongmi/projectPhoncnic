package ds.com.phoncnic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DyningImageDTO {
    
    private Long dno;
    private String menuimagename;
    private String menuimagepath;
    private String backgroundname;
    private String backgroundpath;

    private String id;

}
