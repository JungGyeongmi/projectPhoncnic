package ds.com.phoncnic.dto;

import java.util.ArrayList;
import java.util.List;

import ds.com.phoncnic.entity.Follow;
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
    private Long dno;

    @Builder.Default
    private List<Object> followartistlist = new ArrayList<>();
    @Builder.Default
    private List<Follow> followdyninglist = new ArrayList<>();

    public void setfollower() {
    }

}
