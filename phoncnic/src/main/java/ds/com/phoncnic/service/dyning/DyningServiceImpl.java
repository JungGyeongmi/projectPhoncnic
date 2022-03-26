package ds.com.phoncnic.service.dyning;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.repository.DyningRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class DyningServiceImpl implements DyningService {

  @Autowired
  private final DyningRepository dyningRepository;


  @Transactional
  @Override
  public Long register(DyningDTO dyningdDTO) {
    log.info("dyning/setting/register....");
    Map<String, Object> entityMap = dtoToEntity(dyningdDTO);
    Dyning dyning = (Dyning) entityMap.get("dyning");
    dyningRepository.save(dyning);

    return dyning.getDno();
  }

  @Override
  public List<DyningDTO> getStreet() {
    List<Dyning> result = dyningRepository.getStreetList();
    List<DyningDTO> DyningList = result.stream().map(entity -> roofEntityToDTO(entity)).collect(Collectors.toList());
    return DyningList;
  }
  @Override
  public DyningDTO getDyningDetails(Long dno) {
    Optional<Dyning> dyningList= dyningRepository.findById(dno);
    Dyning dyning = dyningList.get();
    List<DyningImage> dyningImageList = dyningRepository.getImageDetailsPage(dno);
    return entityToDTO(dyning,dyningImageList);
  }
}