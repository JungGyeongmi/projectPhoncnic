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
import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.repository.EmojiRepository;
import ds.com.phoncnic.repository.GalleryRepository;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
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
    private final EmojiInfoService emojiInfoService;

    @Override
    public PageResultDTO<GalleryDTO, Gallery> getPhotoList(PageRequestDTO PageRequestDTO) {
       
        Pageable pageable = PageRequestDTO.getPageable(Sort.by("gno").descending());
        List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();
        Page<Gallery> result = galleryRepository.getPhotoPage(pageable);

        Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity,
                emojiService.getEmojiCountArrayByGno(entity.getGno()), emojiInfoList ));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<GalleryDTO, Gallery> getPaintingList(PageRequestDTO PageRequestDTO) {

        List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();
        Pageable pageable = PageRequestDTO.getPageable(Sort.by("gno").descending());
        Page<Gallery> result = galleryRepository.getPaintingPage(pageable);
        Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity,
                emojiService.getEmojiCountArrayByGno(entity.getGno()),emojiInfoList ) );
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<GalleryDTO, Object[]> getGalleryPage(SearchPageRequestDTO pageRequestDTO) {
        log.info("search page....");
        List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();
        // ??????
        Long[][] emojiArray = new Long[5][2];
      
        Function<Object[], GalleryDTO> fn = (entity -> entityToDTO((Gallery) entity[0], emojiArray, emojiInfoList));
        
        Sort sort = getSort(pageRequestDTO.getSort());
       
        Page<Object[]> result = galleryRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(sort));

        log.info(pageRequestDTO);
        return new PageResultDTO<>(result, fn);
    }


    // ?????? ?????????
    @Override
    public GalleryDTO getGallery(long gno) {
        Gallery gallery = galleryRepository.getGalleryByGno(gno);

        List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();
        Long[][] emojiCountArr = emojiService.getEmojiCountArrayByGno(gno);
        log.info("gno:" + gno);
        log.info("gallery" + gallery);
        return entityToDTO(gallery, emojiCountArr, emojiInfoList);
    }

    // gallery List
    @Override
    public List<GalleryDTO> getGalleryList(Boolean type) {

        List<Gallery> galleryList = galleryRepository.getGalleryList(type);

        List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();

        List<GalleryDTO> galleryDTOList = galleryList.stream()
                .map(entity -> entityToDTO(entity, emojiService.getEmojiCountArrayByGno(entity.getGno()), emojiInfoList))
                .collect(Collectors.toList());
                
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

    @Transactional
    @Override
    public Long register(GalleryDTO galleryDTO) {
        Gallery gallery = dtoToEntity(galleryDTO);
        galleryRepository.save(gallery);
        return gallery.getGno();
    }

    @Override
    public List<GalleryDTO> getUserGallery(String id) {
        List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();
        List<GalleryDTO> galleryDTOList = galleryRepository.findByMemberId(id).stream()
                .map(entity -> entityToDTO(entity, emojiService.getEmojiCountArrayByGno(entity.getGno()), emojiInfoList))
                .collect(Collectors.toList());
        return galleryDTOList;
    }

    // max
    @Override
    public Boolean isItmaxLength(String id) {
        Long maxLength = 10L;
        Object[] obj = galleryRepository.countingGalleryByMemberId("gm950715@gmail.com");
        Long uploadGalleryCount = (Long)obj[0];
        Boolean resultChecker = false;

        log.info("max length : "+maxLength);
        log.info("upload gallery count :"+uploadGalleryCount);

        if (uploadGalleryCount< maxLength) {
            resultChecker = true;
        }

        return resultChecker;
    }
}