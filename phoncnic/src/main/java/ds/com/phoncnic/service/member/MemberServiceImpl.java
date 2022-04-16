package ds.com.phoncnic.service.member;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.ApplicationForm;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.Emoji;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.ApplicationFormRepository;
import ds.com.phoncnic.repository.ApplicationImageRepository;
import ds.com.phoncnic.repository.CharacterLookRepository;
import ds.com.phoncnic.repository.DyningImageRepository;
import ds.com.phoncnic.repository.DyningRepository;
import ds.com.phoncnic.repository.EmojiRepository;
import ds.com.phoncnic.repository.FollowRepository;
import ds.com.phoncnic.repository.GalleryRepository;
import ds.com.phoncnic.repository.HelpRepository;
import ds.com.phoncnic.repository.MemberRepository;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final CharacterLookRepository characterLookRepository;

    private final EmojiRepository emojiRepository;

    private final FollowRepository followRepository;

    private final HelpRepository helpRepository;

    private final GalleryRepository galleryRepository;

    private final DyningRepository dyningRepository;

    private final DyningImageRepository dyningImageRepository;

    private final ApplicationFormRepository applicationFormRepository;

    private final ApplicationImageRepository applicationImageRepository;

    @Override
    public void updateMemberDTO(MemberDTO memberDTO) {
        Member member = dtoToEntity(memberDTO);
        log.info("update member DTO");
        log.info(member);

        log.info("MemberComeOn ....." + member);
        memberRepository.save(member);
        
    }

    @Override
    public MemberDTO getMember(String id) {
        log.info(id);
        Optional<Member> memberOptional = memberRepository.findById(id);
        Member member = memberOptional.get();
        log.info("MemberComeOn ....." + member);
        return entityToDTO(member);
    }

    public void modify2(AuthMemberDTO dto) {
        // findById는 바로 로딩을 해주고, getOne은 필요한 순간까지 로딩을 지연함
        Optional<Member> result = memberRepository.findById(dto.getId());

        if (result.isPresent()) {
            Member member = result.get();
            member.changeNickname(dto.getNickname());

            log.info("modify member by authMememberDTO ... " + member);

            memberRepository.save(member);
        }
    }

    @Override
    @Transactional
    @Modifying
    public void remove(String id) {
        Optional<Member> result = memberRepository.findById(id);

        log.info("get member by id for remove ..." + result);

        if (result.isPresent()) {
            characterLookRepository.deleteByMemberId(id);
            followRepository.deleteByMemberId(id);
            
            List<Dyning> dyninglist = dyningRepository.findByMemberId(id);

            for (Dyning dno : dyninglist)
                emojiRepository.deleteByDno(dno.getDno());

            List<Gallery> gallerylist = galleryRepository.findByMemberId(id);
            
            for (Gallery gno : gallerylist)
                emojiRepository.deleteByGno(gno.getGno());

            List<Emoji> emojilist = emojiRepository.findByMemberId(id);
           
            for (Emoji eno : emojilist)
                emojiRepository.deleteByEno(eno.getEno());

            log.info("dyninglist" + dyninglist);
            log.info("gallerylist" + gallerylist);
            log.info("emojilist" + emojilist);
            // log.info("applylist" + applyilist);
            
            Optional<Dyning> haveDyning = dyningRepository.findDyningByMemberId(id);
           
            if (haveDyning.isPresent()) {
                dyningImageRepository.deleteByDno(haveDyning.get().getDno());
                log.info("---------dno deleted--------------");
            }

            Optional<ApplicationForm> havingApply = applicationFormRepository.findApplicationFormByMemberId(id);

            if (havingApply.isPresent()) {
                applicationImageRepository.deleteByAfno(havingApply.get().getAfno());
                log.info("---------afno deleted--------------");
            }

            dyningRepository.deleteByMemberId(id);
            galleryRepository.deleteByMemberId(id);
            helpRepository.deleteByWriterEmail(id);
            applicationFormRepository.deleteByApplicantId(id);

            log.info("get member by id for delete ...."+memberRepository.findById(id));

            memberRepository.deleteById(id);
        }
    }

    @Override
    public Boolean checkMemberExist(String id) {
        return memberRepository.getMemberByMemberId(id);
    }

    @Override
    public String getNickname(String id) {

        return memberRepository.getNicknameById(id);

    }

    
}
