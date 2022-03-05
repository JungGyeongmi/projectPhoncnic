package ds.com.phoncnic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoofDesignInfoDTO {
    
    private Long rooftype;
    private String roofpath;
    private String kindofroof; 

}
