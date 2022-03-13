package ds.com.phoncnic.service.mypage;

import ds.com.phoncnic.dto.CharacterLookDTO;
import ds.com.phoncnic.entity.CharacterLookInfo;

public interface CharacterLookService {

      
    CharacterLookDTO getCharacterHair(String id);
    CharacterLookDTO getCharacterClothes(String id);
    // void modify(MemberDTO dto);
    // void modify(MyPageDTO myPageDTO);


        default CharacterLookDTO entityToDTO(CharacterLookInfo characterLookinfo){
        CharacterLookDTO characterLookDTO = CharacterLookDTO.builder()
        .hairname(characterLookinfo.getHairname())
        .hairpath(characterLookinfo.getHairpath())
        .clothesname(characterLookinfo.getClothesname())
        .clothespath(characterLookinfo.getClothespath())
        .build();
        return characterLookDTO;
    }
 
    // default CharacterLook dtoToEntity(MyPageDTO mypageDTO) {

    //     CharacterLook characterLook = CharacterLook.builder()
    //     .hairname(mypageDTO.getHairname())
    //     .clothesname(mypageDTO.getClothesname())
    //     .build();

    //     return characterLook;

    // }
}
