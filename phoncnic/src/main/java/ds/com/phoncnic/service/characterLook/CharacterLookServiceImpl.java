package ds.com.phoncnic.service.characterLook;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.entity.CharacterLook;
import ds.com.phoncnic.entity.CharacterLookInfo;
import ds.com.phoncnic.repository.CharacterLookInfoRepository;
import ds.com.phoncnic.repository.CharacterLookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CharacterLookServiceImpl implements CharacterLookService {

    @Autowired
    private CharacterLookInfoRepository repository;

    @Autowired
    private CharacterLookRepository characterLookRepository;

    @Autowired
    private CharacterLookInfoRepository characterLookInfoRepository;

    @Override
    public CharacterLookDTO getCharacterSet(String id) {
      
        CharacterLookInfo characterLookinfo = repository.getSet(id);
       
        return entityToDTO(characterLookinfo);
    }

    @Override
    public void modify(CharacterLookDTO dto, String id) {

        Optional<CharacterLook> result = characterLookRepository.getLnoById(id);

        if (result.isPresent()) {
            CharacterLook characterLook = result.get();
            
            characterLook.changeSetname(dto.getSetname());

            characterLookRepository.save(characterLook);
        }
    }

    @Override
    public List<CharacterLookInfo> lookimageList() {
        List<CharacterLookInfo> dto = characterLookInfoRepository.findAll();
        
        return dto;
    }

    @Override
    public CharacterLookInfo getDefaultAvatar(long chno) {
       
        return repository.getDefaultCharacterLookByChno(chno);
    }
}
