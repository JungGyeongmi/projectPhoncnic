package ds.com.phoncnic.controller.adminController;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchMemberPageRequestDTO;
import ds.com.phoncnic.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {
    
    private final MemberService memberService;

    @Transactional
    @GetMapping("/search")
    public ResponseEntity<PageResultDTO<MemberDTO, Object[]>>getMember(SearchMemberPageRequestDTO pageRequestDTO) {
 
        log.info(pageRequestDTO);
        PageResultDTO<MemberDTO, Object[]> searchResult = memberService.adminSearchPageByMemberId(pageRequestDTO);

        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    @PostMapping("/modify/{roleSet}")
    public String getMemberInfoModify (@PathVariable("roleSet") List<String> roleSet,
        @RequestBody Map<String, String> json,
        MemberDTO memberDTO,
        RedirectAttributes ra) {
        
        Boolean roleChecker = json.get("originRole")==roleSet.get(0);
        Boolean nickChecker = memberService.nickNameChecker(json.get("nickname"));
        
        memberDTO.setId(json.get("id"));
        memberDTO.setRoleSet(roleSet);

        // 닉네임이 중복되고 롤도 바뀌지 않은 경우
        if(roleChecker && nickChecker) {
            return "overlap";
        } else if(!roleChecker && nickChecker) {
        // 닉네임은 중복되나 롤이 바뀐경우
            memberDTO.setNickname(json.get("originNick"));
            memberService.updateMemberDTO(memberDTO);
            return "role";
        }
        // 닉네임도 롤도 바뀌는 경우
        memberDTO.setNickname(json.get("nickname"));
        memberService.updateMemberDTO(memberDTO);
        log.info("modify...");

        return "change";
    }

    @PostMapping("/remove/{removeid}")
    public void memberRemove(@PathVariable("removeid")String removeid) {
        log.info("member removing....." + removeid);
        memberService.remove(removeid);
    }
}
