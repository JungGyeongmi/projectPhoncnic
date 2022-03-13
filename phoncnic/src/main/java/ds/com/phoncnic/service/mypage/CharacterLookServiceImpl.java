package ds.com.phoncnic.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.entity.CharacterLookInfo;
import ds.com.phoncnic.repository.CharacterLookInfoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterLookServiceImpl implements CharacterLookService{
    @Autowired
    private CharacterLookInfoRepository repository;
    
     @Override
    public CharacterLookDTO getCharacterHair(String id){
        CharacterLookInfo characterLookinfo = repository.getHair(id);
        return entityToDTO(characterLookinfo);
    }
    
    @Override
    public CharacterLookDTO getCharacterClothes(String id){
        CharacterLookInfo characterLookinfo = repository.getHair(id);
        return entityToDTO(characterLookinfo);
    }
}
