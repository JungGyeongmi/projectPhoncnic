package ds.com.phoncnic.service.mypage;

import java.util.List;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.entity.CharacterLookInfo;

public interface CharacterLookService {

    List<CharacterLookInfo> lookimageList();

    CharacterLookDTO getCharacterHair(String id);

    CharacterLookDTO getCharacterClothes(String id);

    void modify(CharacterLookDTO characterLookDTO, String id);

    default CharacterLookDTO entityToDTO(CharacterLookInfo characterLookinfo) {
        CharacterLookDTO characterLookDTO = CharacterLookDTO.builder()
                .hairname(characterLookinfo.getHairname())
                .hairpath(characterLookinfo.getHairpath())
                .clothesname(characterLookinfo.getClothesname())
                .clothespath(characterLookinfo.getClothespath())
                .build();
        return characterLookDTO;
    }
}
