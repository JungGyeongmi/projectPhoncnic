package ds.com.phoncnic.service.gallery;

import java.util.List;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;

public interface GalleryService {
    
    void register(GalleryDTO galleryDTO);
    
    void removeWithEmojis(long gno);
    
    void modify(GalleryDTO dto);

    // manage 페이지 list
    List<Gallery> getUserGallery(String id);

    // 상세페이지
    GalleryDTO getGallery(long gno);

    // List
    List<GalleryDTO> getGalleryList(Boolean type);

    // Paging처리
    PageResultDTO<GalleryDTO, Gallery> getPhotoList(PageRequestDTO PageRequestDTO);

    PageResultDTO<GalleryDTO, Gallery> getPaintingList(PageRequestDTO PageRequestDTO);

    default Gallery dtoToEntity(GalleryDTO galleryDTO) {
        Gallery gallery = Gallery.builder()
                .gno(galleryDTO.getGno())
                .title(galleryDTO.getTitle())
                .content(galleryDTO.getContent())
                .imagename(galleryDTO.getImagename()==null?"temp":galleryDTO.getImagename())
                .imagepath(galleryDTO.getImagepath()==null?"D:/temp":galleryDTO.getImagepath())
                .imagetype(galleryDTO.isImagetype())
                .artistid(Member.builder().id(galleryDTO.getId()).build())
                .build();

        return gallery;
    }

    default GalleryDTO entityToDTO(Gallery gallery) {

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