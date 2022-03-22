package ds.com.phoncnic.service.mypage;

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
public class CharacterLookServiceImpl implements CharacterLookService{
    @Autowired
    private CharacterLookInfoRepository repository;
    @Autowired
    private CharacterLookRepository characterLookRepository;
    
     @Override
    public CharacterLookDTO getCharacterHair(String id){
        CharacterLookInfo characterLookinfo = repository.getHair(id);
        return entityToDTO(characterLookinfo);
    }
    
    @Override
    public CharacterLookDTO getCharacterClothes(String id){
        CharacterLookInfo characterLookinfo = repository.getClothes(id);
        return entityToDTO(characterLookinfo);
    }
    @Override
    public void modify(CharacterLookDTO dto,String id) {
         //findById는 바로 로딩을 해주고, getOne은 필요한 순간까지 로딩을 지연함
         Optional<CharacterLook> result = characterLookRepository.getLnoById(id);

      if(result.isPresent()){
        CharacterLook characterLook = result.get();
        characterLook.changeHairname(dto.getHairname());
        characterLook.changeClothesname(dto.getClothesname());
        
        characterLookRepository.save(characterLook);
    }
}
}