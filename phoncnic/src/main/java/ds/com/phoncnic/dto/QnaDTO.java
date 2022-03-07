package ds.com.phoncnic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {
    
    private long qno;
    private String tilte;
    private String content;
    private String password;
    private String qtype;
    private boolean answerstatus;

    private String writer;
}
