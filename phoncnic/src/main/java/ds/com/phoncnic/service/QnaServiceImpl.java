package ds.com.phoncnic.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.GalleryDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.dto.QnaDTO;
import ds.com.phoncnic.entity.Gallery;
import ds.com.phoncnic.entity.Qna;
import ds.com.phoncnic.repository.QnaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService { 
    
    private final QnaRepository qnaRepository;
    
    @Override
    public PageResultDTO getQnaList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("regDate").descending());

        Page<Qna> result = qnaRepository.getListPage(pageable);
        
        Function<Qna, QnaDTO> fn = (entity -> entityToDTO(entity));
        
        return new PageResultDTO<>(result, fn);
    }
}
