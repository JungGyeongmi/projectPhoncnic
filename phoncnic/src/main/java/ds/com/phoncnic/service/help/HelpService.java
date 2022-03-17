package ds.com.phoncnic.service.help;

import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.dto.HelpDTO;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.entity.Help;

public interface HelpService {
    PageResultDTO getQnaList(PageRequestDTO pageRequestDTO);
    Long register(HelpDTO helpDTO);
    HelpDTO get(Long qno);
    void modify(HelpDTO helpDTO);
    void remove(Long qno);



    /* Entity -> DTO */
    default HelpDTO entityToDTO(Help help){
        HelpDTO helpDTO = HelpDTO.builder()
            .qno(help.getQno())
            .title(help.getTitle())
            .content(help.getContent())
            .password(help.getPassword())
            .qtype(help.getQtype())
            .answerstatus(help.isAnswerstatus())
            .moddate(help.getModDate())
            .regdate(help.getRegDate())
            .writer(help.getWriter().getNickname())
        .build();
        
        return helpDTO;
    }

    
    /* DTO -> Entity */
    default Help dtoToEntity(HelpDTO helpDTO) {

        Help help = Help.builder()
            .qno(helpDTO.getQno())
            .title(helpDTO.getTitle())
            .content(helpDTO.getContent())
            .password(helpDTO.getPassword())
            .qtype(helpDTO.getQtype())
            .answerstatus(helpDTO.isAnswerstatus())
            .writer( Member.builder().id(helpDTO.getWriter()).build())
        .build();

        return help;
    }


}
