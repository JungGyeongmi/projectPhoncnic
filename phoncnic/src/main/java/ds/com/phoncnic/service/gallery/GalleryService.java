package ds.com.phoncnic.service.gallery;

import java.util.List;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;

public interface GalleryService {
    
    // 상세페이지
    GalleryDTO getGallery(long gno);

    // List
    List<GalleryDTO> getGalleryList(Boolean type);
    
    // Paging처리
    PageResultDTO<GalleryDTO, Gallery> getPhotoList(PageRequestDTO PageRequestDTO);
    PageResultDTO<GalleryDTO, Gallery> getPaintingList(PageRequestDTO PageRequestDTO);

    default Gallery dtoToEntity (GalleryDTO galleryDto){
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
            .moddate(gallery.getModDate())
            .regdate(gallery.getRegDate())
            .build();

        return galleryDTO;
    }
}