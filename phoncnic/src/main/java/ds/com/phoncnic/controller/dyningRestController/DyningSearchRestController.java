package ds.com.phoncnic.controller.dyningRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchDyningPageRequestDTO;
import ds.com.phoncnic.service.dyning.DyningService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/dyning")
public class DyningSearchRestController {

    @Autowired
    private DyningService dyningService;
    
    @GetMapping("/restaurant/toggle")
    public ResponseEntity<PageResultDTO<DyningDTO, Object[]>> getToggle(SearchDyningPageRequestDTO searchpageRequestDTO) {
        log.info("---------------get toggle rest---------------");
        log.info(searchpageRequestDTO.getKeyword());
        log.info(searchpageRequestDTO.getType());
        PageResultDTO<DyningDTO, Object[]> result = dyningService.getDyningPage(searchpageRequestDTO);
        log.info(dyningService.getDyningPage(searchpageRequestDTO));
        return new ResponseEntity<>(result, HttpStatus.OK); 
    }
}
