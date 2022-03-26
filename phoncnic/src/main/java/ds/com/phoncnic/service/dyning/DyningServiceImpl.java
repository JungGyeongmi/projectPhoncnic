package ds.com.phoncnic.service.dyning;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.repository.DyningImageRepository;
import ds.com.phoncnic.repository.DyningRepository;
import ds.com.phoncnic.repository.RoofDesignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class DyningServiceImpl implements DyningService {

  @Autowired
  private final DyningRepository dyningRepository;

  @Autowired
  private final DyningImageRepository dyningImageRepository;

  @Autowired
  private final RoofDesignRepository roofDesignRepository;

  @Transactional
  @Override
  public Long register(DyningDTO dyningdDTO) {
    log.info("dyning/setting/register....");
    Map<String, Object> entityMap = dtoToEntity(dyningdDTO);
    Dyning dyning = (Dyning) entityMap.get("dyning");
    dyningRepository.save(dyning);
    List<DyningImage> dyningImageList = 
            (List<DyningImage>) entityMap.get("dyningImageList");
    dyningImageList.forEach(dyningImage -> {
    dyningImageRepository.save(dyningImage);
    });
    log.info(dyning.getDno());
    return dyning.getDno();
  }

 

  // @Override
  // public PageResultDTO<DyningDTO, Object[]> getList(PageRequestDTO
  // pageRequestDTO) {

  // Pageable pageable = pageRequestDTO.getPageable(Sort.by("dno"));

  // Page<Object[]> result = dyningRepository.getListPage(pageable);

  // Function<Object[], DyningDTO> fn = (arr -> entityToDTO(
  // (Dyning) arr[0],
  // (List<DyningImage>) (Arrays.asList((DyningImage) arr[1])),
  // (List<RoofDesign>) (Arrays.asList((RoofDesign) arr[2]))
  // ));

  // return new PageResultDTO<>(result, fn);
  // }

  // @Override
  // public DyningDTO getStreet() {
  // List<Dyning> dyning = dyningRepository.getRoofdesign();
  // List<RoofDesign> roof = roofDesignRepository.findAll();
  // return roofEntityToDTO(dyning,roof);
  // }
  // }

  @Override
  public List<DyningDTO> getStreet() {
    List<Dyning> result = dyningRepository.getStreetList();
    List<DyningDTO> DyningList = result.stream().map(entity -> roofEntityToDTO(entity)).collect(Collectors.toList());
    return DyningList;
  }
//   @Override
//   public DyningDTO getDyningDetails(Long dno) {
//     Optional<Dyning> dyningList= dyningRepository.findById(dno);
//     Dyning dyning = dyningList.get();
//     List<DyningImage> dyningImageList = dyningRepository.getImageDetailsPage(dno);
//     return entityToDTO(dyning,dyningImageList);
//   }
// }

@Override
public DyningDTO getDyningDetails(Long dno) {
  List<Object[]> result = dyningRepository.getDyningDetails(dno);
  Dyning dyninglist = (Dyning)result.get(0)[0];
  Long emojiCwt = (Long)result.get(0)[1];
  List<DyningImage> dyningImageList = dyningRepository.getImageDetailsPage(dno);
  return entityToDTO(dyninglist,emojiCwt,dyningImageList);
}


}


