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

    // @Builder.Default
    // private List<GalleryImageDTO> galleryimageDTOList = new ArrayList<>();
    
    private LocalDateTime regdate;
    private LocalDateTime moddate;
}