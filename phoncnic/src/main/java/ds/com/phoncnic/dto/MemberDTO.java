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
public class MemberDTO {
    private String id;
    private String nickname;
    private String password;
    
    
    private LocalDateTime regdate, moddate;
    
}
