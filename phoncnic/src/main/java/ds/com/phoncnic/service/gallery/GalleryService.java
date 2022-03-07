package ds.com.phoncnic.service.gallery;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;

public interface GalleryService {
    
    PageResultDTO<GalleryDTO, Gallery> getList(PageRequestDTO PageRequestDTO);

    default Gallery dtoToEnttity (GalleryDTO galleryDto){
        Gallery gallery = Gallery.builder()
                .gno(galleryDto.getGno())
                .title(galleryDto.getTitle())
                .imagename(galleryDto.getImagename())
                .imagetype(galleryDto.isImagetype())
                .imagepath(galleryDto.getImagepath())
                .artistid(Member.builder().id(galleryDto.getId()).build())
                .build();

            return gallery;
    }


    default  GalleryDTO entityToDTO(Gallery gallery){
        
        GalleryDTO galleryDTO = GalleryDTO.builder()
                .gno(gallery.getGno())
                .title(gallery.getTitle())
                .content(gallery.getContent())
                .imagename(gallery.getImagename())
                .imagetype(gallery.isImagetype())
                .imagepath(gallery.getImagepath())
                .id(gallery.getArtistid().getId())
                .build();

        return galleryDTO;
    }
}