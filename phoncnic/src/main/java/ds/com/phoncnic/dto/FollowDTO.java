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

    private String followerid;

    // private String dyningname;
    // private String artistname;

    private List<Object> dyningname;
    
    private List<Object> artistname;

    
}
