package ds.com.phoncnic.controller.adminController;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.service.member.MemberService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/admin")
public class AdminRestController {
    
    @Autowired
    private MemberService memberService;

    @Transactional
    @PostMapping("/search/{id}")
    public ResponseEntity<MemberDTO>getMember (@PathVariable("id")String id) {
        log.info("getMember....");
        log.info(id);
        
        return new ResponseEntity<>(memberService.getMember(id), HttpStatus.OK);
    }

    @PostMapping("/modify/{role}")
    public void getMemberInfoModify (@RequestBody MemberDTO memberDTO, @PathVariable("role") String role) {
        
        log.info("modify...");
        List<String> roleSet = new ArrayList<>();
        roleSet.add("["+role+"]");
        memberDTO.setRoleSet(roleSet);

        memberService.updateMemberDTO(memberDTO);
    }
}
