package ds.com.phoncnic.service.reception;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.ApplicationFormDTO;
import ds.com.phoncnic.entity.ApplicationForm;
import ds.com.phoncnic.entity.ApplicationImage;
import ds.com.phoncnic.repository.ApplicationFormRepository;
import ds.com.phoncnic.repository.ApplicationImageRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ApplicationFormServiceImpl implements ApplicationFormService {
  
  @Autowired
  ApplicationFormRepository formRepository;

  @Autowired
  ApplicationImageRepository imageRepository;

  @Transactional
  @Override
  public Long register (ApplicationFormDTO dto) {

    log.info("reception/register....");

    Map<String, Object> entityMap = dtoToEntity(dto);
    ApplicationForm apply = (ApplicationForm) entityMap.get("applicationform");

    formRepository.save(apply);

    if(dto.getApplicationtype()){
      log.info("register img....");
      List<ApplicationImage> imglist = (List<ApplicationImage>) entityMap.get("businessregistration");
  
      imglist.forEach(image -> {
        imageRepository.save(image);
      });
    }

    log.info(apply.getAfno());

    return apply.getAfno();
  }

  @Override
  public ApplicationFormDTO applicationExistsCheckerByUserId(String id) {

    List<ApplicationForm> checker = formRepository.findByMemberId(id);

    if(checker.size()==0) {
      return ApplicationFormDTO.builder().build();
    }
    return  entityToDTO(checker.get(0));
  }

  @Override
  public Long isItMaxReception(String id) {
   
    Long afno = 0L;
    Boolean checker = formRepository.existCheckerByMemberId(id);
    
    if(checker) {
      List<ApplicationForm> applyList = formRepository.findByMemberId(id);
      afno = applyList.get(0).getAfno();
    }

    return afno;
  }

  @Override
  public Boolean updateConfirmState(String id, Boolean checker) {
    log.info("check ..." +checker);
    Boolean exist = formRepository.existCheckerByMemberId(id);
    
    log.info(exist);
    if(!exist) {
      return false;
    }
    List<ApplicationForm> applyList = formRepository.findByMemberId(id);
    ApplicationForm apply = applyList.get(0);
    log.info(apply);
    apply.setConfirm(checker);
    
    formRepository.save(apply);
    log.info(apply);
    
    log.info("update apply confirm..");
    return true;
  }
}
