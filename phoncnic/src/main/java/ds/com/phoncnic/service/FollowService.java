package ds.com.phoncnic.service;

import java.util.Arrays;
import java.util.List;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.entity.Follow;

public interface FollowService {

    FollowDTO getFollow(String id);    
    
    default FollowDTO entityToDTO(List<Object> artistList,List<Object> dyningList){
        FollowDTO followDTO = FollowDTO.builder()
        .artistname(artistList)
        .dyningname(dyningList)
        .build();
        return followDTO;
    }
}
