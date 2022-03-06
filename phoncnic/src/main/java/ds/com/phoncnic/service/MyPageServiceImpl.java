package ds.com.phoncnic.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.MyPageDTO;
import ds.com.phoncnic.entity.CharacterLookInfo;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.CharacterLookInfoRepository;
import ds.com.phoncnic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {
    @Autowired
    private CharacterLookInfoRepository CharacterLookInfoRepository;

    @Autowired
    private MemberRepository memberRepository;
    
    @Override
    public MyPageDTO getMyPage(String id) {
    CharacterLookInfo chin = CharacterLookInfoRepository.getCharacterImgs(id);    
    Optional<Member> memberOptional = memberRepository.findById(id);
    Member member = memberOptional.get();
    // return entitiesToDTO((String)arr[1], (String)arr[2], (Long)arr[3],(Long)arr[4]);
      return entitiesToMyPageDTO(member ,chin);
    
  }

    // @Override
    // public PageResultDTO<MemberDTO, Member> getList(PageRequestDTO pageRequestDTO){
    //     //원하는 페이지 번호와 갯수를 정렬과 합께 초기화
    //     Pageable pageable = pageRequestDTO.getPageable(Sort.by("regDate").descending());
    //     //result를 레파지토리 초기화된 pageable과 repository의 findAll메서드를 통해 담음

    //     Page<Member> result = memberRepository.findAll(pageable);//Querydsl 사용
        
    //     Function<Member,MemberDTO> fn = (member -> entitiesToDTO(member);
    //     //pageResultDTO의 생성자를 통해 list타입의 멤버변수에 결과를 담음.
    //     return new PageResultDTO<>(result, fn);        
        
    // }


     
}
