package ds.com.phoncnic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacterLookDTO {
    private Long lno;
    private String setname;
    private String setpath;

    private String setbackname;
    private String setbackpath;
}
