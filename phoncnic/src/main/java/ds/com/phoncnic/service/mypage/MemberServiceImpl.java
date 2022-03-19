package ds.com.phoncnic.service.mypage;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.CharacterLookRepository;
import ds.com.phoncnic.repository.DyningImageRepository;
import ds.com.phoncnic.repository.DyningRepository;
import ds.com.phoncnic.repository.EmojiRepository;
import ds.com.phoncnic.repository.FollowRepository;
import ds.com.phoncnic.repository.GalleryRepository;
import ds.com.phoncnic.repository.HelpRepository;
import ds.com.phoncnic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CharacterLookRepository characterLookRepository;
    @Autowired
    private EmojiRepository emojiRepository;
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private HelpRepository helpRepository;
    @Autowired
    private GalleryRepository galleryRepository;
    @Autowired
    private DyningRepository dyningRepository;
    @Autowired
    private DyningImageRepository dyningImageRepository;
    

    
    @Override
    public MemberDTO getMember(String id) {
    Optional<Member> memberOptional = memberRepository.findById(id);
    Member member = memberOptional.get();
      return entityToDTO(member);
    
  }

  @Override
  public void modify(MemberDTO memberDTO){
      //findById는 바로 로딩을 해주고, getOne은 필요한 순간까지 로딩을 지연함
      Optional<Member> result = memberRepository.findById(memberDTO.getId());


      if(result.isPresent()){
        Member member = result.get();
        member.changeNickname(memberDTO.getNickname());
        member.changePassword(memberDTO.getPassword());
        memberRepository.save(member);
      }

  }

  @Transactional
  @Override
  public void remove(String id) {
    Optional<Member> result = memberRepository.findById(id);
    if(result.isPresent()){
      characterLookRepository.deleteByMemberId(id);
      followRepository.deleteByMemberId(id);
      
      List<Dyning> dyninglist= dyningRepository.findByMemberId(id);
      for (Dyning dno: dyninglist)emojiRepository.deleteByDno(dno.getDno());

      List<Gallery> gallerylist= galleryRepository.findByMemberId(id);
      for (Gallery gno: gallerylist)emojiRepository.deleteByGno(gno.getGno());

      Long dno =dyningRepository.findDyningByMemberId(id).get().getDno();
      dyningImageRepository.deleteByDno(dno);
      dyningRepository.deleteByMemberId(id);
      galleryRepository.deleteByMemberId(id);
      helpRepository.deleteByMemberId(id);
      
      Member member = result.get();
      memberRepository.delete(member);
    }

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
