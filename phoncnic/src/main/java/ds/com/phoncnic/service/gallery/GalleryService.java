package ds.com.phoncnic.service.gallery;

import java.util.List;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;

public interface GalleryService {

    // 등록
    void register(GalleryDTO dto);

    // 삭제
    void removeWithEmojis(long gno);

    // 수정
    void modify(GalleryDTO dto);


    // 상세페이지
    GalleryDTO getGallery(long gno);

    // List
    List<GalleryDTO> getGalleryList(Boolean type);
    
    // Paging처리
    PageResultDTO<GalleryDTO, Gallery> getPhotoList(PageRequestDTO PageRequestDTO);
    PageResultDTO<GalleryDTO, Gallery> getPaintingList(PageRequestDTO PageRequestDTO);

    default Gallery dtoToEntity (GalleryDTO galleryDTO){
        Gallery gallery = Gallery.builder()
            .gno(galleryDTO.getGno())
            .title(galleryDTO.getTitle())
            .content(galleryDTO.getContent())
            .imagename(galleryDTO.getImagename())
            .imagetype(galleryDTO.isImagetype())
            .imagepath(galleryDTO.getImagepath())
            .artistid( Member.builder().id(galleryDTO.getId()).build() )
            .build();

            return gallery;
    }


    default  GalleryDTO entityToDTO(Gallery gallery){
        String path = gallery.getImagepath()==null?"D:/":gallery.getImagepath();
        GalleryDTO galleryDTO = GalleryDTO.builder()
            .gno(gallery.getGno())
            .title(gallery.getTitle())
            .content(gallery.getContent())
            .imagename(gallery.getImagename())
            .imagetype(gallery.isImagetype())
            .imagepath(path)
            .id(gallery.getArtistid().getId())
            .moddate(gallery.getModDate())
            .regdate(gallery.getRegDate())
            .build();

        return galleryDTO;
    }
}