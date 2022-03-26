package ds.com.phoncnic.controller.galleryRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchPageRequestDTO;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/gallery")
@Log4j2
public class GalleryRestController {

    @Autowired
    private GalleryService galleryService;

    @GetMapping("/read/{gno}")
    public ResponseEntity<GalleryDTO> getRead(@PathVariable("gno") Long gno) {
        log.info("post Gallery details......... rest "+gno);
        GalleryDTO galleryDTO = galleryService.getGallery(gno);
        log.info("galleryDTO : " + galleryDTO);
        return new ResponseEntity<>(galleryDTO, HttpStatus.OK);
    }
    
    @GetMapping("/curator")
    public ResponseEntity<PageResultDTO<GalleryDTO, Object[]>> getCuratorModal(SearchPageRequestDTO pageRequestDTO,
        String type, String sort, String keyword) {

        log.info("---------------get curator rest---------------");
        log.info("type"+type);
        log.info("sort"+sort);
        log.info("keyword"+keyword);
        pageRequestDTO.setType(type);
        pageRequestDTO.setSort(sort);
        pageRequestDTO.setKeyword(keyword);
        PageResultDTO<GalleryDTO, Object[]> result = galleryService.getGalleryPage(pageRequestDTO);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/curator")
    public ResponseEntity<PageResultDTO<GalleryDTO, Object[]>> getSearchData(SearchPageRequestDTO pageRequestDTO,
        String type, String sort, String keyword) {


        log.info("---------------post curator---------------");
        log.info("type"+type);
        log.info("sort"+sort);
        log.info("keyword"+keyword);
        pageRequestDTO.setType(type);
        pageRequestDTO.setSort(sort);
        pageRequestDTO.setKeyword(keyword);
        
        PageResultDTO<GalleryDTO, Object[]> result = galleryService.getGalleryPage(pageRequestDTO);

        log.info("gallery rest controller ... curator ...");

        log.info(result.getDtoList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
