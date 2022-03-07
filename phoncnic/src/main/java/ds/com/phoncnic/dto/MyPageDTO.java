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
public class MyPageDTO {
    private String id;
    private String nickname;
    private String password;
    
    private String hairname;
    private String hairpath;
    private String clothesname;
    private String clothespath;
    
    private LocalDateTime regdate, moddate;
    
}
