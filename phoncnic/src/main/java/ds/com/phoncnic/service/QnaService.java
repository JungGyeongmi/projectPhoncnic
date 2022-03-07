package ds.com.phoncnic.service;

import java.util.List;

import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.dto.QnaDTO;
import ds.com.phoncnic.entity.Qna;
import ds.com.phoncnic.entity.Member;

public interface QnaService {
    PageResultDTO getQnaList(PageRequestDTO pageRequestDTO);


    /* Entity -> DTO */
    default QnaDTO entityToDTO(Qna qna){
        // List<EmojiDTO> emojiDTOList = new ArrayList<>();
        
        QnaDTO qnaDTO = QnaDTO.builder()
            .qno(qna.getQno())
            .tilte(qna.getTitle())
            .content(qna.getContent())
            .password(qna.getPassword())
            .qtype(qna.getQtype())
            .answerstatus(qna.isAnswerstatus())
            .writer(qna.getWriter().getNickname())
        .build();
        
        return qnaDTO;
    }

    
    /* DTO -> Entity */
    default Qna dtoToEntity(QnaDTO qnaDTO) {

        Qna qna = Qna.builder()
            .qno(qnaDTO.getQno())
            .title(qnaDTO.getTilte())
            .content(qnaDTO.getContent())
            .password(qnaDTO.getPassword())
            .qtype(qnaDTO.getQtype())
            .answerstatus(qnaDTO.isAnswerstatus())
            .writer( Member.builder().id(qnaDTO.getWriter()).build())
        .build();

        return qna;
    }


}
