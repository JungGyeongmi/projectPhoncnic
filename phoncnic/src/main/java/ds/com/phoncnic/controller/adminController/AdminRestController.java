package ds.com.phoncnic.controller.adminController;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchMemberPageRequestDTO;
import ds.com.phoncnic.service.member.MemberService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/admin")
public class AdminRestController {
    
    @Autowired
    private MemberService memberService;

    @Transactional
    @PostMapping("/search/{keyword}/{type}/{sort}")
    public ResponseEntity<PageResultDTO<MemberDTO, Object[]>>getMember (@PathVariable("keyword")String keyword, 
        @PathVariable("type")String type, @PathVariable("sort")String sort, 
        SearchMemberPageRequestDTO pageRequestDTO) {
 
        pageRequestDTO.setKeyword(keyword);
        pageRequestDTO.setType(type);
        pageRequestDTO.setSort(sort);

        log.info(pageRequestDTO);
        PageResultDTO<MemberDTO, Object[]> searchResult = memberService.adminSearchPageByMemberId(pageRequestDTO);
        
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    @PostMapping("/modify/{roleSet}")
    public void getMemberInfoModify (@PathVariable("roleSet") List<String> roleSet, MemberDTO memberDTO) {
        
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
