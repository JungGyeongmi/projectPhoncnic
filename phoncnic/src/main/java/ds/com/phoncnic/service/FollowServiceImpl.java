package ds.com.phoncnic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.repository.FollowRepository;
import ds.com.phoncnic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private MemberRepository memberRepository;

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
}
