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
public class HelpDTO {
    
    private long qno;
    private String title;
    private String content;
    private String password;
    private String qtype;
    private boolean answerstatus;
    private String writeremail;

    private LocalDateTime regdate, moddate;
}
