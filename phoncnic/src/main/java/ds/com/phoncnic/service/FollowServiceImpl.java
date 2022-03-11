package ds.com.phoncnic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.FollowDTO;
import ds.com.phoncnic.entity.Follow;
import ds.com.phoncnic.repository.FollowRepository;
import ds.com.phoncnic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{
    @Autowired
    private FollowRepository followRepository;
    @Autowired 
    private MemberRepository memberRepository;

    @Override
    public FollowDTO getFollow(String id){
        // Optional<Member> memberOptional = memberRepository.findById(id);
        Follow follow=followRepository.getFollowList(id);
        return entityToDTO(follow);
    }
}
