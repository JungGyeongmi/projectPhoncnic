package ds.com.phoncnic.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DyningDTO {
    
    private long dno;

    private String dyningname;
    private String location;
    private Long foodtype;
    private String businesshours;
    private String comment;
    private String hashtag;

    private LocalDateTime regdate, moddate;

    private String id;

    private long oono;
    

    @Builder.Default 
    private List<DyningImageDTO> dyningImageDTOList = new ArrayList<>();

    @Builder.Default
    private List<RoofDesignDTO> roofDesignDTOList  = new ArrayList<>();
}
