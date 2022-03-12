package ds.com.phoncnic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.FollowDTO;
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
    public FollowDTO getFollow(String id) {
        List artistList = followRepository.getartistnameList(id);
        List dyningList = followRepository.getdyningnameList(id);
        return entityToDTO(artistList, dyningList);
    }
}
