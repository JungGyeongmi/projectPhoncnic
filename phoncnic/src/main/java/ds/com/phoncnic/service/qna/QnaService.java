package ds.com.phoncnic.service.qna;

import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.dto.QnaDTO;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.entity.Qna;

public interface QnaService {
    PageResultDTO getQnaList(PageRequestDTO pageRequestDTO);
    Long register(QnaDTO qnaDTO);


    /* Entity -> DTO */
    default QnaDTO entityToDTO(Qna qna){
        QnaDTO qnaDTO = QnaDTO.builder()
            .qno(qna.getQno())
            .title(qna.getTitle())
            .content(qna.getContent())
            .password(qna.getPassword())
            .qtype(qna.getQtype())
            .answerstatus(qna.isAnswerstatus())
            .moddate(qna.getModDate())
            .regdate(qna.getRegDate())
            .writer(qna.getWriter().getNickname())
        .build();
        
        return qnaDTO;
    }

    
    /* DTO -> Entity */
    default Qna dtoToEntity(QnaDTO qnaDTO) {

        Qna qna = Qna.builder()
            .qno(qnaDTO.getQno())
            .title(qnaDTO.getTitle())
            .content(qnaDTO.getContent())
            .password(qnaDTO.getPassword())
            .qtype(qnaDTO.getQtype())
            .answerstatus(qnaDTO.isAnswerstatus())
            .writer( Member.builder().id(qnaDTO.getWriter()).build())
        .build();

        return qna;
    }


}
