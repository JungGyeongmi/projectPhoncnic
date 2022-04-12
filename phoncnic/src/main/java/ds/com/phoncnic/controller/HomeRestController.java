package ds.com.phoncnic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.security.dto.AuthMemberDTO;
import ds.com.phoncnic.service.characterLook.CharacterLookService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/character")
public class HomeRestController {

    @Autowired
    CharacterLookService characterLookService;


    @GetMapping("/lookmodal")
    public ResponseEntity<Object[]> test2 (@AuthenticationPrincipal AuthMemberDTO dto, Model model) {
    
        Object[] result = new Object[3];

        result[0] = characterLookService.lookimageList();

        if(dto!=null){
            log.info("id:" + dto.getId());
            result[1] = dto.getId();
            result[2] = characterLookService.getCharacterSet(dto.getId());
        } else{
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<> (result, HttpStatus.OK);
    }

    @PostMapping("/lookmodify")
    public ResponseEntity<Void> lookmodify(@RequestBody CharacterLookDTO characterLookDTO, @AuthenticationPrincipal AuthMemberDTO dto) {
        log.info("character look modify....");
        log.info(characterLookDTO);
        try {
            characterLookService.modify(characterLookDTO, dto.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
