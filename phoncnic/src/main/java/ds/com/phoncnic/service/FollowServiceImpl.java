package ds.com.phoncnic.service;

import java.util.List;

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
        return entityToDTO(artistList,dyningList);
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
    public Long getFnoByGno(String id, String artistname) {
        log.info("serviceImplid:-------------------"+id+artistname);
        
        Long fno = followRepository.getFnoIfFollowedByGno(id, artistname);

        log.info("serviceImplfno:-------------------"+fno);

        return fno;
    }
}
