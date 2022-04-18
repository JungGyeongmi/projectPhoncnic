package ds.com.phoncnic.controller.adminController;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void getMemberInfoModify (@PathVariable("roleSet") List<String> roleSet,
        @RequestBody Map<String, String> json,
        MemberDTO memberDTO) {
        
        memberDTO.setId(json.get("id"));
        memberDTO.setNickname(json.get("nickname"));
        memberDTO.setRoleSet(roleSet);

        log.info("modify...");

        memberService.updateMemberDTO(memberDTO);

    }

    @PostMapping("/remove/{removeid}")
    public void memberRemove(@PathVariable("removeid")String removeid) {
        log.info("member removing....." + removeid);
        memberService.remove(removeid);
    }
}
