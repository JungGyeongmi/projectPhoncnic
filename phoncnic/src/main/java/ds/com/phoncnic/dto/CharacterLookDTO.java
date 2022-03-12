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
    private String hairname;
    private String hairpath;
    private String clothesname;
    private String clothespath;

}
