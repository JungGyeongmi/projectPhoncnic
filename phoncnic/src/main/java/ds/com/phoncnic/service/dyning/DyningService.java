package ds.com.phoncnic.service.dyning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.DyningImageDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.entity.RoofDesign;

public interface DyningService {
    // PageResultDTO<DyningDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    Long register(DyningDTO dyningDTO);

    // void modify(DyningDTO dyningDTO);

    // void removeWithAll(long dno);

    // void addemoji(long dno);

    // 가게 상세페이지
    DyningDTO getDyningDetails(Long dno);

    default Map<String, Object> dtoToEntity(DyningDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();

        Member member = Member.builder().id(dto.getId()).build();

        RoofDesign roofDesign = RoofDesign.builder().oono(dto.getOono()).build();

        Dyning dyning = Dyning.builder()
                .dno(dto.getDno())
                .dyningname(dto.getDyningname())
                .location(dto.getLocation())
                .businesshours(dto.getBusinesshours())
                .comment(dto.getComment())
                .tel(dto.getTel())
                .hashtag(dto.getHashtag())
                .roofdesign(roofDesign)
                .ceoid(member)
                .build();

        entityMap.put("dyning", dyning);

        List<DyningImageDTO> dyningImageDTOList = dto.getDyningImageDTOList();

        if (dyningImageDTOList != null && dyningImageDTOList.size() > 0) {
            List<DyningImage> dyningImageList = dyningImageDTOList.stream().map(DyningImageDTO -> {
                DyningImage dyningImage = DyningImage.builder()
                        .menuimagename(DyningImageDTO.getMenuimagename())
                        .menuimagepath(DyningImageDTO.getMenuimagepath())
                        .backgroundname(DyningImageDTO.getBackgroundname())
                        .backgroundpath(DyningImageDTO.getBackgroundpath())
                        .dyning(dyning)
                        .build();

                return dyningImage;
            }).collect(Collectors.toList());

            entityMap.put("dyningImageList", dyningImageList);
        }

        return entityMap;

    }

    default DyningDTO entityToDTO(Dyning dyning,Long emojiCwt, List<DyningImage> dyningImages) {
        DyningDTO dyningDTO = DyningDTO.builder()
                .dno(dyning.getDno())
                .dyningname(dyning.getDyningname())
                .location(dyning.getLocation())
                .businesshours(dyning.getBusinesshours())
                .comment(dyning.getComment())
                .tel(dyning.getTel())
                .hashtag(dyning.getHashtag())
                .tel(dyning.getTel())
                .id(dyning.getCeoid().getId())
                .oono(dyning.getRoofdesign().getOono())
                .emojicwt(emojiCwt)
                .regdate(dyning.getRegDate())
                .moddate(dyning.getModDate())
                .build();

        List<DyningImageDTO> dyningImageDTOList = dyningImages.stream().map(dyningImage -> {
            return DyningImageDTO.builder()
                    .backgroundname(dyningImage.getBackgroundname())
                    .backgroundpath(dyningImage.getBackgroundpath())
                    .menuimagename(dyningImage.getMenuimagename())
                    .menuimagepath(dyningImage.getMenuimagepath())
                    .dno(dyning.getDno())
                    .id(dyning.getCeoid().getId())
                    .build();
        }).collect(Collectors.toList());

        dyningDTO.setDyningImageDTOList(dyningImageDTOList);

        return dyningDTO;

    }

    // 가게 리스트
    List<DyningDTO> getStreet();

    default DyningDTO roofEntityToDTO(Dyning dyning) {
        DyningDTO dyningDTO = DyningDTO.builder()
                .dno(dyning.getDno())
                .dyningname(dyning.getDyningname())
                .roofpath(dyning.getRoofdesign().getRoofpath())
                .foodtype(dyning.getFoodtype())
                .build();

        return dyningDTO;
    }

    // default DyningDTO JustRoofEntityToDTO(RoofDesign roofDesign) {
    // DyningDTO dyningDTO = DyningDTO.builder()
    // .roofpath(dyning.getRoofdesign().getRoofpath())
    // .build();

    // return dyningDTO;
    // }

}
