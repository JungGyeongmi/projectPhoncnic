package ds.com.phoncnic.controller.galleryRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.repository.GalleryRepository;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/gallery")
public class GalleryRestController {

    @Autowired
    private GalleryService galleryService;
    @Autowired
    private GalleryRepository galleryRepository;


    @PostMapping("/read/{gno}")
    public ResponseEntity<GalleryDTO> getRead(@PathVariable("gno") Long gno) {
        log.info("post Gallery details......... rest "+gno);
        log.info(gno.getClass());
        GalleryDTO galleryDTO = galleryService.getGallery(gno);
        log.info("galleryDTO : " + galleryDTO);
        return new ResponseEntity<>(galleryDTO, HttpStatus.OK);
    }
    
    @GetMapping("/modal/search")
    public ResponseEntity<PageResultDTO<GalleryDTO, Gallery>> getSearchData(PageRequestDTO pageRequestDTO, String keyword, String type, String sort) {

        Pageable pageable = PageRequest.of(0, 2, Sort.by("gno").descending());

        Page<Object[]> page = galleryRepository.searchPage("t", "title", pageable);


        PageResultDTO<GalleryDTO, Gallery> result = galleryService.getPaintingList(pageRequestDTO);  
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
   
}
