package ds.com.phoncnic.service.dyning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.DyningImageDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchDyningPageRequestDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.entity.RoofDesign;

public interface DyningService {

    List<RoofDesign> roofimageList();

    Long register(DyningDTO dyningDTO);

    void removeWithImages(Long dno);

    void modify(DyningDTO dyningDTO);

    default Map<String, Object> dtoToEntity(DyningDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();

        Member member = Member.builder().id(dto.getId()).build();

        RoofDesign roofDesign = RoofDesign.builder().oono(dto.getOono()).build();

        Dyning dyning = Dyning.builder()
                .dno(dto.getDno())
                .dyningname(dto.getDyningname())
                .location(dto.getLocation())
                .locationdetails(dto.getLocationdetails())
                .businesshours(dto.getBusinesshours())
                .comment(dto.getComment())
                .foodtype(dto.getFoodtype())
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
                        .menuimageuuid(DyningImageDTO.getMenuimageuuid())
                        .menuimagepath(DyningImageDTO.getMenuimagepath())
                        .backgroundname(DyningImageDTO.getBackgroundname())
                        .backgrounduuid(DyningImageDTO.getBackgrounduuid())
                        .backgroundpath(DyningImageDTO.getBackgroundpath())
                        .dyning(dyning)
                        .build();

                return dyningImage;
            }).collect(Collectors.toList());

            entityMap.put("dyningImageList", dyningImageList);
        }
        return entityMap;
    }

    default DyningDTO entityToDTO(Dyning dyning, Long emojiCwt, List<DyningImage> dyningImages, Long followerCwt) {

        DyningDTO dyningDTO = DyningDTO.builder()
                .dno(dyning.getDno())
                .dyningname(dyning.getDyningname())
                .location(dyning.getLocation())
                .locationdetails(dyning.getLocationdetails())
                .businesshours(dyning.getBusinesshours())
                .comment(dyning.getComment())
                .tel(dyning.getTel())
                .hashtag(dyning.getHashtag())
                .tel(dyning.getTel())
                .id(dyning.getCeoid().getId())
                .foodtype(dyning.getFoodtype())
                .roofthumbnail(dyning.getRoofdesign().getRoofthumbnail())
                .oono(dyning.getRoofdesign().getOono())
                .emojicwt(emojiCwt)
                .followercwt(followerCwt)
                .regdate(dyning.getRegDate())
                .moddate(dyning.getModDate())
                .build();

        List<DyningImageDTO> dyningImageDTOList = dyningImages.stream().map(dyningImage -> {
            return DyningImageDTO.builder()
                    .backgroundname(dyningImage.getBackgroundname())
                    .backgrounduuid(dyningImage.getBackgrounduuid())
                    .backgroundpath(dyningImage.getBackgroundpath())
                    .menuimagename(dyningImage.getMenuimagename())
                    .menuimageuuid(dyningImage.getMenuimageuuid())
                    .menuimagepath(dyningImage.getMenuimagepath())
                    .dno(dyning.getDno())
                    .id(dyning.getCeoid().getId())
                    .build();
        }).collect(Collectors.toList());

        dyningDTO.setDyningImageDTOList(dyningImageDTOList);

        return dyningDTO;
    }

    // List<DyningDTO> getStreet();
    // 카페 거리
    List<DyningDTO> getCafeStreet();

    // 레스토랑 거리
    List<DyningDTO> getRestaurantStreet();

    List<DyningDTO> getMyDyningList(String id);

    // DyningDTO getRoof();
    default DyningDTO roofEntityToDTO(Dyning dyning) {
        DyningDTO dyningDTO = DyningDTO.builder()
                .dno(dyning.getDno())
                .dyningname(dyning.getDyningname())
                .roofpath(dyning.getRoofdesign().getRoofpath())
                .roofthumbnail(dyning.getRoofdesign().getRoofthumbnail())
                .id(dyning.getCeoid().getId())
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

    // 가게 상세페이지
    DyningDTO getDyningDetails(Long dno);

    // 토글에 띄울 dyning page
    PageResultDTO<DyningDTO, Object[]> getDyningPage(SearchDyningPageRequestDTO searchPageRequestDTO);

    default DyningDTO entitiesToDTO(Dyning dyning) {
        DyningDTO dto = DyningDTO.builder()
                .dno(dyning.getDno())
                .dyningname(dyning.getDyningname())
                .hashtag(dyning.getHashtag())
                .build();
        return dto;
    }

    default List<DyningImage> imagesDTOToEntity(DyningDTO dyningDTO) {
        List<DyningImageDTO> dyningImageDTOList = dyningDTO.getDyningImageDTOList();

        List<DyningImage> dyningImageList = dyningImageDTOList.stream().map(DyningImageDTO -> {
            DyningImage dyningImage = DyningImage.builder()
                    .menuimagename(DyningImageDTO.getMenuimagename())
                    .menuimageuuid(DyningImageDTO.getMenuimageuuid())
                    .menuimagepath(DyningImageDTO.getMenuimagepath())
                    .backgroundname(DyningImageDTO.getBackgroundname())
                    .backgrounduuid(DyningImageDTO.getBackgrounduuid())
                    .backgroundpath(DyningImageDTO.getBackgroundpath())
                    .dyning(Dyning.builder().dno(dyningDTO.getDno()).build())
                    .build();

            return dyningImage;
        }).collect(Collectors.toList());

        return dyningImageList;
    }

}
