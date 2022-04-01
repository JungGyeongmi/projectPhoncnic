package ds.com.phoncnic.service.gallery;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchPageRequestDTO;
import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.service.emoji.EmojiInfoService;


public interface GalleryService {

  void register(GalleryDTO galleryDTO);

  void removeWithEmojis(long gno);

  void modify(GalleryDTO dto);

  // manage 페이지 list
  List<GalleryDTO> getUserGallery(String id);

  // 상세페이지
  GalleryDTO getGallery(long gno);

  // List
  List<GalleryDTO> getGalleryList(Boolean type);

  // 모달 창에 띄울 gallery page
  PageResultDTO<GalleryDTO, Object[]> getGalleryPage(SearchPageRequestDTO pageRequestDTO);

  // Paging처리
  PageResultDTO<GalleryDTO, Gallery> getPhotoList(PageRequestDTO PageRequestDTO);

  PageResultDTO<GalleryDTO, Gallery> getPaintingList(PageRequestDTO PageRequestDTO);

  default Gallery dtoToEntity(GalleryDTO galleryDTO) {
    Gallery gallery = Gallery.builder()
        .gno(galleryDTO.getGno())
        .title(galleryDTO.getTitle())
        .content(galleryDTO.getContent())
        .imagename(galleryDTO.getImagename() == null ? "temp" : galleryDTO.getImagename())
        .imagepath(galleryDTO.getImagepath() == null ? "D:/temp" : galleryDTO.getImagepath())
        .imagetype(galleryDTO.isImagetype())
        .uuid(galleryDTO.getUuid())
        .artistid(Member.builder().id(galleryDTO.getId()).build())
        .build();

    return gallery;
  }

  
    

  default GalleryDTO entityToDTO(Gallery gallery, Long[][] emojiArr, List<EmojiInfo> emojiList) {
    
    HashMap<String, String> emojiinfo = new HashMap<>();
    emojiList.stream().forEach(emoji -> {
      emojiinfo.put(emoji.getEmojitype(), emoji.getEmojipath());
    });

    GalleryDTO galleryDTO = GalleryDTO.builder()
        .gno(gallery.getGno())
        .title(gallery.getTitle())
        .content(gallery.getContent())
        .imagename(gallery.getImagename())
        .emojicount(emojiArr)
        .imagetype(gallery.isImagetype())
        .imagepath(gallery.getImagepath())
        .uuid(gallery.getUuid())
        .id(gallery.getArtistid().getId())
        .emojiinfo(emojiinfo)
        .moddate(gallery.getModDate())
        .regdate(gallery.getRegDate())
        .build();
    return galleryDTO;
  }

  default Sort getSort(String sortkeyword) {
    Sort sort = Sort.by("gno").descending();
    if (sortkeyword != null) {
      switch (sortkeyword) {
        case "a":
          sort = Sort.by("gno").ascending();
          break;
        case "d":
          sort = Sort.by("gno").descending();
          break;
      }
    }
    return sort;
  }
}