package ds.com.phoncnic.service.reception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ds.com.phoncnic.dto.ApplicationFormDTO;
import ds.com.phoncnic.dto.ApplicationImageDTO;
import ds.com.phoncnic.entity.ApplicationForm;
import ds.com.phoncnic.entity.ApplicationImage;
import ds.com.phoncnic.entity.Member;

public interface ApplicationFormService {

  Long register(ApplicationFormDTO dto);

  Long isItMaxReception(String id);

  Boolean updateConfirmState(String id, Boolean checker);

  ApplicationFormDTO applicationExistsCheckerByUserId(String id);

  default Map<String, Object> dtoToEntity(ApplicationFormDTO dto) {
    Map<String, Object> entityMap = new HashMap<>();

    Member member = Member.builder().id(dto.getApplicant()).build();

    ApplicationForm apply = ApplicationForm.builder()
        .applicationtype(dto.getApplicationtype())
        .content(dto.getContent())
        .dyningname(dto.getDyningname())
        .member(member)
        .confirm(false)
        .build();

    entityMap.put("applicationform", apply);

    List<ApplicationImageDTO> applicationImageDTOList = dto.getApplicationImageDTOList();

    if (applicationImageDTOList != null && applicationImageDTOList.size() > 0) {
      List<ApplicationImage> appliImagelist = applicationImageDTOList.stream().map(ApplicationImageDTO -> {
        ApplicationImage image = ApplicationImage.builder()
            .imagename(ApplicationImageDTO.getImagename())
            .imagepath(ApplicationImageDTO.getImagepath())
            .uuid(ApplicationImageDTO.getUuid())
            .applicationform(apply)
            .build();

        return image;

      }).collect(Collectors.toList());

      entityMap.put("businessregistration", appliImagelist);
    } else {

    }

    return entityMap;
  }

  default List<ApplicationImage> imagesDTOToEntity(ApplicationFormDTO dto) {

    List<ApplicationImageDTO> applicationImageDTOList = dto.getApplicationImageDTOList();

    List<ApplicationImage> appliImagelist = applicationImageDTOList.stream().map(ApplicationImageDTO -> {
      ApplicationImage image = ApplicationImage.builder()
          .imagename(ApplicationImageDTO.getImagename())
          .imagepath(ApplicationImageDTO.getImagepath())
          .uuid(ApplicationImageDTO.getUuid())
          .applicationform(ApplicationForm.builder().afno(dto.getAfno()).build())
          .build();

      return image;

    }).collect(Collectors.toList());

    return appliImagelist;
  }

  default ApplicationFormDTO entityToDTO(ApplicationForm applicationForm) {

    ApplicationFormDTO applyDTO = ApplicationFormDTO.builder()
      .afno(applicationForm.getAfno())
      .dyningname(applicationForm.getDyningname())
      .applicant(applicationForm.getMember().getId())
      .applicationtype(applicationForm.getApplicationtype())
      .applydate(applicationForm.getApplyDate())
      .confirm(applicationForm.getConfirm())
      .confirmdate(applicationForm.getConfirmDate())
    .build();

    return applyDTO;
  }
}
