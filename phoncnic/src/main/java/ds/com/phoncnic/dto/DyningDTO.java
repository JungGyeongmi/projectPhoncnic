package ds.com.phoncnic.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private String locationdetails;
    private Long foodtype;
    private String businesshours;
    private String comment;
    private String hashtag;
    private LocalDateTime regdate, moddate;
    private String tel;

    private String id;
    private Long emojicwt;

    private Long followercwt;
    private Long oono;
    private String roofpath;
    private String roofthumbnail;

    private HashMap<String, String> emojiinfo;

    @Builder.Default
    private List<DyningImageDTO> dyningImageDTOList = new ArrayList<>();

}
