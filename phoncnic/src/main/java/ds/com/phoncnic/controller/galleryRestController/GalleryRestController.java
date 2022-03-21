package ds.com.phoncnic.controller.galleryRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.service.gallery.GalleryService;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/gallery")
public class GalleryRestController {

    @Autowired
    private GalleryService galleryService;


    @PostMapping("/read/{gno}")
    public ResponseEntity<GalleryDTO> getRead(@PathVariable("gno") Long gno) {
        log.info("post Gallery details......... rest "+gno);
        log.info(gno.getClass());
        GalleryDTO galleryDTO = galleryService.getGallery(gno);
        log.info("galleryDTO : " + galleryDTO);
        return new ResponseEntity<>(galleryDTO, HttpStatus.OK);
    }
    
   

}
