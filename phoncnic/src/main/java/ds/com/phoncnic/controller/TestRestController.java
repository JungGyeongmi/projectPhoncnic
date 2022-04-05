package ds.com.phoncnic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchPageRequestDTO;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@RestController
public class TestRestController {

    @Autowired
    private GalleryService galleryService;


    @GetMapping("/api/test")
    public ResponseEntity<PageResultDTO<GalleryDTO, Object[]>> getSearchData(SearchPageRequestDTO pageRequestDTO,
            String type, String sort, String keyword) {

        pageRequestDTO.setType(type);
        pageRequestDTO.setSort(sort);
        pageRequestDTO.setKeyword(keyword);

        PageResultDTO<GalleryDTO, Object[]> result = galleryService.getGalleryPage(pageRequestDTO);

        log.info("gallery rest controller ... curator ...");

        log.info(result.getDtoList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
