package ds.com.phoncnic.service.follow;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FollowServiceImpl implements FollowService {
    
    @Autowired
    private FollowRepository followRepository;

    @Override
    public FollowDTO getFollow(String id){
        List<Object> artistList = followRepository.getfollowArtistList(id);
        List<Object> dyningList = followRepository.getfollowDyningList(id);
        return entityToDTO(artistList, dyningList);
    }

    @Override
    public void removeArtistFollow(String id, String name){
        Follow follow = followRepository.getFollownameArtist(id, name);
        followRepository.delete(follow);
    }

    @Override
    public void removeDyningFollow(String id, String name){
        Follow follow = followRepository.getFollownameDyning(id, name);
        followRepository.delete(follow);
    }

    @Override
    public Long addArtistFollow(FollowDTO followDTO) {
        Follow follow = dtoToEntity(followDTO);
        followRepository.save(follow);

        log.info("-------------------------fno:"+follow.getFno());

        return follow.getFno();
    }

    @Override
    public Long addDyningFollow(FollowDTO followDTO) {
        Follow follow = dtoToEntity(followDTO);
        followRepository.save(follow);
        return follow.getFno();
    }

    @Override
    public Long getFno(String id, String dyningname) {
        Long fno = followRepository.getFnoIfFollowed(id, dyningname);
        return fno;
    }

    @Override
    public Long getGalleryFno(String id, String artistname) {
        log.info("serviceImplid:-------------------"+id+" "+artistname);
        
        Long fno = followRepository.getGalleryFno(id, artistname);
        
        log.info("serviceImplfno:-------------------"+fno);

        return fno;
    }

    @Transactional
    @Override
    public Object[] galleryfollowRegister(FollowDTO followDTO) {

        String id = followDTO.getFollowerid();
        String artistname = followDTO.getArtistname();
        Follow galleryFollow = Follow.builder().build();

        //0 fno 1 artistname 2 followerid 3 boolean
        List<Object[]> followList =followRepository.getFollowArtist(id, artistname);

        if(followList.isEmpty()){
            log.info("followList--");
            Long fno = followRepository.getGalleryFno(id,artistname);
            Follow follow = dtoToEntity(followDTO);
            followRepository.save(follow);
            Object[] arr ={follow.getFno(), follow.getArtistname(),follow.getFollower().getId(),(fno!=null)?true:false};
            return arr;
        }

        Object[] follow = followRepository.getFollowArtist(id, artistname).get(0);

        if(!(boolean) follow[3]) {
            galleryFollow = dtoToEntity(followDTO);
            followRepository.save(galleryFollow);
            log.info("isnert follow....."+galleryFollow.getFno());
        } else if(follow[2].equals(id) && follow[1].equals(artistname)) {
            log.info("delete fno....."+(Long)follow[0]);
            followRepository.deleteByFno((Long) follow[0]);
        }
        return follow;
    }

    @Override
    public Long getGalleryFollower(String nickname) {
        Object[] countobj = followRepository.countFollowerByUserNickNameInGallery(nickname);
        Long countFollower = (Long)countobj[0];
        return countFollower;
    }
}