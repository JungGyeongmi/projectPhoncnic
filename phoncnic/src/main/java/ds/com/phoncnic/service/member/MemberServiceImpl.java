package ds.com.phoncnic.service.member;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.Emoji;
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


    @Override
    public void updateMemberDTO(MemberDTO memberDTO) {
        Member member = dtoToEntity(memberDTO);

        memberRepository.save(member);
        log.info("MemberComeOn ....." + member);

    }

    @Override
    public MemberDTO getMember(String id) {
        Optional<Member> memberOptional = memberRepository.findById(id);
        Member member = memberOptional.get();
        return entityToDTO(member);
    }

    public void modify2(AuthMemberDTO dto) {
        // findById는 바로 로딩을 해주고, getOne은 필요한 순간까지 로딩을 지연함
        Optional<Member> result = memberRepository.findById(dto.getId());

        if (result.isPresent()) {
            Member member = result.get();
            member.changeNickname(dto.getNickname());
            log.info("Memberrrrr"+member);
            memberRepository.save(member);
        }
    }

    

    @Override
    @Transactional
    @Modifying
    public void remove(String id) {
        Optional<Member> result = memberRepository.findById(id);
        log.info("dtoResult"+result);
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
                log.info("dyninglist"+dyninglist);
                log.info("gallerylist"+gallerylist);
                log.info("emojilist"+emojilist);
                Optional<Dyning> haveDyning = dyningRepository.findDyningByMemberId(id);
                if (haveDyning.isPresent()) { 
            dyningImageRepository.deleteByDno(haveDyning.get().getDno());
            log.info("---------dno deleted--------------");
        }
            dyningRepository.deleteByMemberId(id);
            galleryRepository.deleteByMemberId(id);
            helpRepository.deleteByMemberId(id);
            log.info(memberRepository.findById(id));
            memberRepository.deleteById(id);
        }
    }
}
