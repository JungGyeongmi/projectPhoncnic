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
import ds.com.phoncnic.service.reception.ApplicationFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {
    
    private final MemberService memberService;
    private final ApplicationFormService formService;

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
        
        String oriNick = json.get("originNick");
        Boolean roleChecker = json.get("originRole").equals(roleSet.get(0));
        Boolean nickChecker = memberService.nickNameChecker(json.get("nickname"));
        Boolean confirmChecker = json.get("confirmCheck").equals("0")?false:true;
        String id = json.get("id");

        memberDTO.setId(id);
        memberDTO.setRoleSet(roleSet);
        log.info(confirmChecker);
        Boolean updateConfirm = formService.updateConfirmState(id, confirmChecker);
        
        
        String message = updateConfirm?"a":"";
        
        // ???????????? ???????????? ?????? ????????? ?????? ??????
        if(roleChecker && nickChecker) {
            return "b"+message;
        } else if(!roleChecker && nickChecker) {
        // ???????????? ???????????? ?????? ????????????
            memberDTO.setNickname(oriNick);
            memberService.updateMemberDTO(memberDTO);
            return "c"+message;
        }
        // ???????????? ?????? ????????? ??????
        memberDTO.setNickname(json.get("nickname"));
        memberService.updateMemberDTO(memberDTO);
        log.info("modify...");

        return "d"+message;
    }

    @PostMapping("/remove/{removeid}")
    public void memberRemove(@PathVariable("removeid")String removeid) {
        log.info("member removing....." + removeid);
        memberService.remove(removeid);
    }
}
