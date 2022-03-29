package ds.com.phoncnic.service.gallery;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchPageRequestDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.repository.EmojiRepository;
import ds.com.phoncnic.repository.GalleryRepository;
import ds.com.phoncnic.service.emoji.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;
    private final EmojiRepository emojiRepository;
    private final EmojiService emojiService;

    @Override
    public PageResultDTO<GalleryDTO, Gallery> getPhotoList(PageRequestDTO PageRequestDTO) {
        Pageable pageable = PageRequestDTO.getPageable(Sort.by("gno").descending());

        Page<Gallery> result = galleryRepository.getPhotoPage(pageable);
        Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity, emojiService.getEmojiCountArrayByGno(entity.getGno())));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<GalleryDTO, Gallery> getPaintingList(PageRequestDTO PageRequestDTO) {
        Pageable pageable = PageRequestDTO.getPageable(Sort.by("gno").descending());
        Page<Gallery> result = galleryRepository.getPaintingPage(pageable);
        Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity, emojiService.getEmojiCountArrayByGno(entity.getGno())));
<<<<<<< HEAD
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<GalleryDTO, Object[]> getGalleryPage(SearchPageRequestDTO pageRequestDTO) {
        // 임시로 3L로 고정
        Long[][] emojiArray = emojiService.getEmojiCountArrayByGno(3L);
        Function<Object[], GalleryDTO> fn = (entity -> entityToDTO((Gallery)entity[0], emojiArray));

        Page<Object[]> result = galleryRepository.searchPage(
            pageRequestDTO.getType(), 
            pageRequestDTO.getKeyword(),
            pageRequestDTO.getPageable(Sort.by("gno").descending()) );

        return new PageResultDTO<>(result, fn);
    }

=======
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<GalleryDTO, Object[]> getGalleryPage(SearchPageRequestDTO pageRequestDTO) {
        // 임시로 3L로 고정
        Long[][] emojiArray = emojiService.getEmojiCountArrayByGno(3L);
        Function<Object[], GalleryDTO> fn = (entity -> entityToDTO((Gallery)entity[0], emojiArray));
        Sort sort = getSort(pageRequestDTO.getSort());
        Page<Object[]> result = galleryRepository.searchPage(
            pageRequestDTO.getType(), 
            pageRequestDTO.getKeyword(),
            pageRequestDTO.getPageable(sort)
        );
        return new PageResultDTO<>(result, fn);
    }

>>>>>>> T_main

    // 상세 페이지
    @Override
    public GalleryDTO getGallery(long gno) {
        Gallery gallery = galleryRepository.getGalleryByGno(gno);
        Long[][] emojiCountArr = emojiService.getEmojiCountArrayByGno(gno);
<<<<<<< HEAD
        // List<Object[]> emojiList = emojiRepository.getEmojiCountByGno(gno);

        
        log.info("gno:"+gno);
        log.info("gallery"+gallery);
    
        return entityToDTO(gallery, emojiCountArr);

=======
        log.info("gno:"+gno);
        log.info("gallery"+gallery);
        return entityToDTO(gallery, emojiCountArr);
>>>>>>> T_main
    }

    // gallery List
    @Override
    public List<GalleryDTO> getGalleryList(Boolean type) {
        List<Gallery> galleryList = galleryRepository.getGalleryList(type);

        List<GalleryDTO> galleryDTOList = galleryList.stream()
            .map(entity -> entityToDTO(entity,emojiService.getEmojiCountArrayByGno(entity.getGno())))
            .collect(Collectors.toList());
<<<<<<< HEAD

=======
>>>>>>> T_main
        return galleryDTOList;
    }

    // remove with emoji
    @Transactional
    @Override
    public void removeWithEmojis(long gno) {
        emojiRepository.deleteByGno(gno);
        galleryRepository.deleteById(gno);
    }

    @Override
    public void modify(GalleryDTO dto) {
        log.info(dto.toString());
        Gallery gallery = galleryRepository.findById(dto.getGno()).get();
        gallery.changeTitleAndContent(dto.getTitle(), dto.getContent());
        galleryRepository.save(gallery);
    }

    @Override
    public void register(GalleryDTO galleryDTO) {
        Gallery gallery = dtoToEntity(galleryDTO);
        galleryRepository.save(gallery);
    }

    @Override
    public List<Gallery> getUserGallery(String id) {
       return galleryRepository.findByMemberId(id);
    }
}