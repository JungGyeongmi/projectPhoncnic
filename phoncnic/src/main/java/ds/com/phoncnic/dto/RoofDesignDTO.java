package ds.com.phoncnic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoofDesignDTO {
    
    private Long oono;

    private Long rooftype; 
    private String roofname; 
    private String roofpath;

    


}
