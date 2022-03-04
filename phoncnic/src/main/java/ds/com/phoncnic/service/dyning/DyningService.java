package ds.com.phoncnic.service.dyning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.DyningImageDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.dto.RoofDesignDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.entity.RoofDesign;

public interface DyningService {

    Long register(DyningDTO dyningDTO);

    
    default Map<String, Object> dtoToEntity(DyningDTO dto) {
        Map<String, Object> entityMap = new HashMap<>();
        
        Member member = Member.builder().id(dto.getId()).build();
        
        Dyning dyning = Dyning.builder()
                .dno(dto.getDno())
                .dyningname(dto.getDyningname())
                // .roofdesign(dto.getRoofdesign()) 
                .location(dto.getLocation())
                .foodtype(dto.getFoodtype())
                .businesshours(dto.getBusinesshours())
                .comment(dto.getComment())
                .hashtag(dto.getHashtag())
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

                List<RoofDesignDTO> roofDesignDTOList = dto.getRoofDesignDTOList();

                if(roofDesignDTOList != null && roofDesignDTOList.size() > 0) {
            List<RoofDesign> roofDesignList = roofDesignDTOList.stream().map(RoofDesignDTO -> {
                 RoofDesign  roofDesign = RoofDesign.builder()
                        .roofdesigntype(RoofDesignDTO.getRoofdesigntype())
                        .dyning(dyning)
                        .build();

                return  roofDesign;              
                }).collect(Collectors.toList());

                entityMap.put("roofDesign", roofDesignList);
            }
        return entityMap;
            
    }

    PageResultDTO<DyningDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
    
    default DyningDTO entityToDTO(Dyning dyning, List<DyningImage> dyningImages) {
        DyningDTO dyningDTO = DyningDTO.builder()
                .dno(dyning.getDno())
                .dyningname(dyning.getDyningname())
                // .roofdesign(dyning.getRoofdesign())
                .location(dyning.getLocation())
                .foodtype(dyning.getFoodtype())
                .businesshours(dyning.getBusinesshours())
                .comment(dyning.getComment())
                .hashtag(dyning.getHashtag())
                .id(dyning.getCeoid().getId())
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

}