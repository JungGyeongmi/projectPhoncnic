package ds.com.phoncnic.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FollowDTO {

    private String followerid;

    // private String dyningname;
    // private String artistname;

    private List<String> dyningname;

    private List<String> artistname;

}
