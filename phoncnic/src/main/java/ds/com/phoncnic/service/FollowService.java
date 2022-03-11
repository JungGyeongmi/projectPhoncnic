package ds.com.phoncnic.service;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.entity.Member;

public interface FollowService {

    FollowDTO getFollow(String id);    
    
    default FollowDTO entityToDTO(Follow follow){
        FollowDTO followDTO = FollowDTO.builder()
        .followerid(follow.getFollower().getId())
        .artistname(follow.getArtistname())
        .dyningname(follow.getDyningname())
        .build();
        return followDTO;
    }
}
