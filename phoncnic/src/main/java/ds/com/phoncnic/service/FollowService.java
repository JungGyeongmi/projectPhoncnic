package ds.com.phoncnic.service;

import java.util.List;

import ds.com.phoncnic.dto.FollowDTO;

public interface FollowService {

    FollowDTO getFollow(String id);    
    
    default FollowDTO entityToDTO(List artistList,List dyningList){
        FollowDTO followDTO = FollowDTO.builder()
        .artistname(artistList)
        .dyningname(dyningList)
        .build();
        return followDTO;
    }
}
