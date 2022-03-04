package ds.com.phoncnic.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GalleryDTO {
    
    private Long gno;
    private String title;
    private String  content;
    private boolean imagetype;
    private String imagepath;

    //Member id
    private String id;
    
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}