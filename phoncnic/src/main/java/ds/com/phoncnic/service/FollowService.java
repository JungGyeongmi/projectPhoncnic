package ds.com.phoncnic.service;

import java.util.List;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.entity.Follow;

public interface FollowService {

    FollowDTO getFollow(String id);
    void removeArtistFollow(String id,String name);
    void removeDyningFollow(String id,String name);
    default FollowDTO entityToDTO(List<Object> artistList,List<Object> dyningList){
        FollowDTO followDTO = FollowDTO.builder()

       .followartistlist(artistList)
       .followdyninglist(dyningList)
        .build();
        return followDTO;
    }
}
