package ds.com.phoncnic.service.characterLook;

import java.util.List;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.entity.CharacterLookInfo;

public interface CharacterLookService {

    List<CharacterLookInfo> lookimageList();
    CharacterLookDTO getCharacterSet(String id);
    void modify(CharacterLookDTO characterLookDTO, String id);
    CharacterLookInfo getDefaultAvatar(long chno);

    default CharacterLookDTO entityToDTO(CharacterLookInfo characterLookinfo) {
        CharacterLookDTO characterLookDTO = CharacterLookDTO.builder()
                .setname(characterLookinfo.getSetname())
                .setpath(characterLookinfo.getSetpath())
                .setbackpath(characterLookinfo.getSetbackpath())
                .build();
        return characterLookDTO;
    }
}
