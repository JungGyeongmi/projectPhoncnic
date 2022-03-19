package ds.com.phoncnic.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmojiDTO {
    private Long eno;
    
    private String id;
    private Long dno;
    private Long gno;

    private String emojitype;
    private String emojipath;
    private String kindofemoji;
    
    private LocalDateTime regdate, moddate;
}
