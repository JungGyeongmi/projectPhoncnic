package ds.com.phoncnic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.CharacterLook;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.CharacterLookRepository;
import ds.com.phoncnic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    
  @Autowired
  private CharacterLookRepository characterLookRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Override
  public MemberDTO getMyPage(String id) {
    Object result = characterLookRepository.getMypageData(id);
    Object[] arr = (Object[]) result;
    return entitiesToMyPageDTO((Member) arr[0], (CharacterLook) arr[1]);
  }

  @Override
  public void modify(MemberDTO memberDTO) {
    
    Optional<Member> result = memberRepository.findById(memberDTO.getId());
    
    if (result.isPresent()) {
      Member member = result.get();
      member.changeNickname(memberDTO.getId());

      memberRepository.save(member);
    }
  }
}