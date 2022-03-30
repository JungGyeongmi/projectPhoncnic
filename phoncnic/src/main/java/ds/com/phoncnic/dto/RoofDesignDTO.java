package ds.com.phoncnic.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    private String roofthumbnail;
    private String roofpath;

}
