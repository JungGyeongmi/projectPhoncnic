package ds.com.phoncnic.service.help;

import java.lang.reflect.Member;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.HelpDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Help;
import ds.com.phoncnic.repository.HelpRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService { 
    
    private final HelpRepository helpRepository;
    
    @Override
    public PageResultDTO getQnaList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("regDate").descending());

        Page<Help> result = helpRepository.getListPage(pageable);
        
        Function<Help, HelpDTO> fn = (entity -> entityToDTO(entity));
        
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Long register(HelpDTO helpDTO) {
        Help entity = dtoToEntity(helpDTO);
        Help helpEntity = Help.builder()
            .title("답변")
            .content("답변내용")
            .password(helpDTO.getPassword())
            .qtype(helpDTO.getQtype()+"answer")
            .answerstatus(false)
            .writer(Member.builder().id("user10@icloud.com").build())
            .build();
        helpRepository.save(entity);
        helpRepository.save(helpEntity);
        return entity.getQno();

    }


    @Override
    public HelpDTO get(Long qno) {
        Optional<Help> result = helpRepository.findById(qno);

        if(result.isPresent()) {
            return entityToDTO(result.get());        
        }
        return null;
    }

    @Override
    public void modify(HelpDTO helpDTO) {
        Optional<Help> result = helpRepository.findById(helpDTO.getQno());
        if(result.isPresent()){
            Help help = result.get();

            help.changeTitle(helpDTO.getTitle());
            help.changeContent(helpDTO.getContent());
            help.changeQtype(helpDTO.getQtype());

            helpRepository.save(help);
        }
    }

    @Override
    public void remove(Long qno) {
        helpRepository.deleteById(qno);
        helpRepository.deleteById(qno-1);
    }

}

