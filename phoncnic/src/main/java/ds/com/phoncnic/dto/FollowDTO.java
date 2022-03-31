package ds.com.phoncnic.dto;

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
public class FollowDTO {

    private Long fno;
    private String followerid;
    private String artistname;
    private String dyningname;

    @Builder.Default
    private List<Object> followartistlist = new ArrayList<>();
    @Builder.Default
    private List<Object> followdyninglist = new ArrayList<>();

}
