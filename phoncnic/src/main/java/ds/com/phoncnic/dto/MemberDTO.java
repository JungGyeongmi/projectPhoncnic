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
public class MemberDTO {
    private String id;
    private String nickname;
    
    private LocalDateTime regdate, moddate;
    
    @Builder.Default
    private List<String> roleSet = new ArrayList<>();
}
