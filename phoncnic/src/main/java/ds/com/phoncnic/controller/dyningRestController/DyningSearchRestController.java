package ds.com.phoncnic.controller.dyningRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
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

    // @GetMapping("/restaurant/search")
    // public void search1(String keyword, @ModelAttribute("pageRequestDTO")
    // PageRequestDTO pageRequestDTO, Model model) {
    // // log.info("dno: ");
    // }

    // @GetMapping("/restaurant/search")
    // public void search1(PageRequestDTO pageRequestDTO, Model model) {
        
    // }

    @GetMapping("/restaurant/toggle")
    public ResponseEntity<PageResultDTO<DyningDTO, Object[]>> getToggle(SearchDyningPageRequestDTO searchpageRequestDTO) {
        log.info("---------------get toggle rest---------------");
        PageResultDTO<DyningDTO, Object[]> result = dyningService.getDyningPage(searchpageRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK); 
    }

    // @GetMapping("/curator")
    // public ResponseEntity<PageResultDTO<GalleryDTO, Object[]>> getCuratorModal(SearchPageRequestDTO pageRequestDTO) {
    //     log.info("---------------get curator rest---------------");
    //     PageResultDTO<GalleryDTO, Object[]> result = galleryService.getGalleryPage(pageRequestDTO);
    //     return new ResponseEntity<>(result, HttpStatus.OK);
    // }

}
