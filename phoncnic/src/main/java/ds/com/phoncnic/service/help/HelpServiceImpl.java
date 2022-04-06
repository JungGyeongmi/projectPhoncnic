package ds.com.phoncnic.service.help;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.HelpDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.entity.Help;
import ds.com.phoncnic.repository.HelpRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService { 
    
    private final HelpRepository helpRepository;
    
    @Override
    public PageResultDTO<HelpDTO, Object[]> getQnaList(PageRequestDTO pageRequestDTO) {
        
        Function<Object[], HelpDTO> fn = (entity -> entityToDTO((Help)entity[0]));
        Page<Object[]> result = helpRepository.searchPage(
            pageRequestDTO.getType(), 
            pageRequestDTO.getKeyword(),
            pageRequestDTO.getPageable(Sort.by("qno").descending()) );
            
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Long register(HelpDTO helpDTO) {
        Help entity = dtoToEntity(helpDTO);
        helpRepository.save(entity);
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
