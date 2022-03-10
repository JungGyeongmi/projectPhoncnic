package ds.com.phoncnic.service.gallery;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {
   
    private final GalleryRepository galleryRepository;
   
    @Override
    public PageResultDTO<GalleryDTO, Gallery> getPhotoList(PageRequestDTO PageRequestDTO) {
        Pageable pageable = PageRequestDTO.getPageable(Sort.by("gno").descending());

        Page<Gallery> result = galleryRepository.getPhotoPage(pageable);
        
        Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity));
        
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public PageResultDTO<GalleryDTO, Gallery> getPaintingList(PageRequestDTO PageRequestDTO) {
        Pageable pageable = PageRequestDTO.getPageable(Sort.by("gno").descending());

        Page<Gallery> result = galleryRepository.getPaintingPage(pageable);
        
        Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity));
        
        return new PageResultDTO<>(result, fn);
    }

    // 상세 페이지
    @Override
    public GalleryDTO getGallery(long gno) {        
        Gallery gallery = galleryRepository.getGalleryByGno(gno);
        return entityToDTO(gallery);
    }

    // gallery List
    @Override
    public List<GalleryDTO> getGalleryList(Boolean type) {
        List<Gallery> galleryList = galleryRepository.getGalleryList(type);
        
        List<GalleryDTO> galleryDTOList = galleryList.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
        
        return galleryDTOList;
    }
}
