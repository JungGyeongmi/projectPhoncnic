package ds.com.phoncnic.service.gallery;

import java.util.function.Function;

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
   
    // @Override
    // public PageResultDTO<GalleryDTO, Gallery> getList(PageRequestDTO PageRequestDTO) {
    //     Pageable pageable = PageRequestDTO.getPageable(Sort.by("regDate").descending());

    //     Page<Gallery> result = galleryRepository.getListPage(pageable);
        
    //     Function<Gallery, GalleryDTO> fn = (entity -> entityToDTO(entity));
        
    //     return new PageResultDTO<>(result, fn);
    // }
}
