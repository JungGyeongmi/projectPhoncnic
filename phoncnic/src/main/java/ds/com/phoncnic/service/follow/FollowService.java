package ds.com.phoncnic.service.follow;

import java.util.List;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.entity.Member;

public interface FollowService {

    FollowDTO getFollow(String id);

    void removeArtistFollow(String id, String name);

    Object[] galleryfollowRegister(FollowDTO followDTO);

    void removeDyningFollow(String id, String name);

    Long addArtistFollow(FollowDTO followDTO);

    Long addDyningFollow(FollowDTO followDTO);

    Long getFno(String id, String dyningname);

    Long getGalleryFno(String id, String artistname);

    Long getGalleryFollower(String nickname);
    
    default FollowDTO entityToDTO(List<Object> artistList, List<Object> dyningList) {
        FollowDTO followDTO = FollowDTO.builder()
                .followartistlist(artistList)
                .followdyninglist(dyningList)
                .build();
        return followDTO;
    }

    default Follow dtoToEntity(FollowDTO followDTO){
        if(followDTO.getArtistname()==null){

            Follow follow = Follow.builder()
            .follower(Member.builder().id(followDTO.getFollowerid()).build())
            .dyningname(followDTO.getDyningname())
            .build();
            return follow;
        }else{
            Follow follow = Follow.builder()
            .follower(Member.builder().id(followDTO.getFollowerid()).build())
            .artistname(followDTO.getArtistname())
            .build(); 
            return follow;
        }
    }
}
