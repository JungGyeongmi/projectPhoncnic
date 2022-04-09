package ds.com.phoncnic.service.mypage;

import java.util.List;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.entity.CharacterLookInfo;

public interface CharacterLookService {

    List<CharacterLookInfo> lookimageList();

    CharacterLookDTO getCharacterSet(String id);

    void modify(CharacterLookDTO characterLookDTO, String id);

    default CharacterLookDTO entityToDTO(CharacterLookInfo characterLookinfo) {
        CharacterLookDTO characterLookDTO = CharacterLookDTO.builder()
                .setname(characterLookinfo.getSetname())
                .setpath(characterLookinfo.getSetpath())
                .build();
        return characterLookDTO;
    }
}
