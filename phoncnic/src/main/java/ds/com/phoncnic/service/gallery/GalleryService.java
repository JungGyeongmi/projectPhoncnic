package ds.com.phoncnic.service.gallery;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;

public interface GalleryService {
    
    // PageResultDTO<GalleryDTO, Gallery> getList(PageRequestDTO PageRequestDTO);

    default Gallery dtoToEntity(GalleryDTO galleryDTO) {

        Gallery gallery = Gallery.builder()
        .gno(galleryDTO.getGno())
        .title(galleryDTO.getTitle())
        .content(galleryDTO.getContent())
        .artistid(
            Member.builder()
            .id(galleryDTO.getId())
            .build()
        )
        .build();

        return gallery;
    }

    default GalleryDTO entityToDTO(Gallery gallery) {

        GalleryDTO galleryDTO = GalleryDTO.builder()
            .gno(gallery.getGno())
            .title(gallery.getTitle())
            .content(gallery.getContent())
            .id(gallery.getArtistid().getId())
            .regdate(gallery.getRegDate())
            .moddate(gallery.getModDate())
            .build();
            return galleryDTO;
        }
}