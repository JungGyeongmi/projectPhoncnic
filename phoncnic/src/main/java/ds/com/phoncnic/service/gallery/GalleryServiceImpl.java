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
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.repository.EmojiRepository;
import ds.com.phoncnic.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;
    private final EmojiRepository emojiRepository;

    @Override
    public PageResultDTO<GalleryDTO, Gallery> getPhotoList(PageRequestDTO PageRequestDTO) {
        Pageable pageable = PageRequestDTO.getPageable(Sort.by("gno").descending());

        Page<Gallery> result = galleryRepository.getPhotoPage(pageable);
        
        Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity, emojiRepository.getEmojiCountByGno(entity.getGno())));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<GalleryDTO, Gallery> getPaintingList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("gno").descending());

        Page<Gallery> result = galleryRepository.getPaintingPage(pageable);

        Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity, emojiRepository.getEmojiCountByGno(entity.getGno())));

        return new PageResultDTO<>(result, fn);
    }

    // 상세 페이지
    @Override
    public GalleryDTO getGallery(long gno) {
        Gallery gallery = galleryRepository.getGalleryByGno(gno);
        List<Object[]> emojiList = emojiRepository.getEmojiCountByGno(gno); 
        
        log.info("gno:"+gno);
        log.info("gallery"+gallery);
        log.info("emojiList(cnt):"+emojiList.get(0)[1]);

        return entityToDTO(gallery, emojiList);
    }

    // gallery List
    @Override
    public List<GalleryDTO> getGalleryList(Boolean type) {
        List<Gallery> galleryList = galleryRepository.getGalleryList(type);

        List<GalleryDTO> galleryDTOList = galleryList.stream()
            .map(entity -> entityToDTO(entity, emojiRepository.getEmojiCountByGno(entity.getGno())))
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