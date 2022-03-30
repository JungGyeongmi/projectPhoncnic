package ds.com.phoncnic.service.dyning;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.RoofDesignDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.entity.QDyning;
import ds.com.phoncnic.entity.RoofDesign;
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
    List<DyningImage> dyningImageList = (List<DyningImage>) entityMap.get("dyningImageList");
    dyningImageList.forEach(dyningImage -> {
      dyningImageRepository.save(dyningImage);
    });
    log.info(dyning.getDno());
    return dyning.getDno();
  }

  @Override
  public List<RoofDesign> roofimageList(){
    List<RoofDesign> dto = roofDesignRepository.findAll();
    return dto;
  };

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

  @Override
  public List<DyningDTO> getMyDyningList(String id) {
    List<Dyning> result = dyningRepository.findByMemberId(id);
    List<DyningDTO> DyningList = result.stream().map(entity -> roofEntityToDTO(entity)).collect(Collectors.toList());
    return DyningList;
  }

  @Transactional
  @Modifying
  @Override
  public void removeWithImages(Long dno) {
    dyningImageRepository.deleteByDno(dno);    
    dyningRepository.deleteByDno(dno);
  }

  @Transactional
  @Modifying
  @Override
  public void modify(DyningDTO dyningDTO) {
    log.info(dyningDTO.getDyningImageDTOList());

    Dyning dyning = dyningRepository.findById(dyningDTO.getDno()).get();
    dyningImageRepository.deleteByDno(dyningDTO.getDno());
    List<DyningImage> dyningImageList = imagesDTOToEntity(dyningDTO) ;
    if(!dyningImageList.isEmpty()){
    dyningImageList.forEach(dyningImage -> {
      dyningImageRepository.save(dyningImage);
    });}
    RoofDesign roof = roofDesignRepository.findById(dyningDTO.getOono()).get();
    dyning.modifyDyning(dyningDTO.getDyningname(),dyningDTO.getComment(),dyningDTO.getLocation(),dyningDTO.getFoodtype(),
    dyningDTO.getBusinesshours(),dyningDTO.getTel(),dyningDTO.getHashtag(),roof);
    dyningRepository.save(dyning);
  }

  @Override
  public DyningDTO getDyningDetails(Long dno) {
    List<Object[]> result = dyningRepository.getDyningDetails(dno);
    Dyning dyninglist = (Dyning) result.get(0)[0];
    Long emojiCwt = (Long) result.get(0)[1];
    List<DyningImage> dyningImageList = dyningRepository.getImageDetailsPage(dno);
    return entityToDTO(dyninglist, emojiCwt, dyningImageList);
  }



  @Override
  // 결과값을 결국 RageResultDTO로 받음
  public PageResultDTO<DyningDTO, Dyning> getList(PageRequestDTO pageRequestDTO) {

    // 원하는 페이지의 번호와 갯수를 정렬과 함께 Pageable 초기화
    Pageable pageable = pageRequestDTO.getPageable(Sort.by("dno"));

    // 검색조건을 위한 객체 생성
    BooleanBuilder builder = getSearch(pageRequestDTO);

    // 초기화된 Pageable과 repository를 통해서 결과를 담음
    Page<Dyning> result = dyningRepository.findAll(builder, pageable);

    // 스트림에서 Page객체의 결과를 처리하기 위한 내용을 담은 함수
    Function<Dyning, DyningDTO> fn = (entity -> entitiesToDTO(entity));
    return new PageResultDTO<>(result, fn);
  }

  private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
    String type = pageRequestDTO.getType();

    BooleanBuilder builder = new BooleanBuilder();

    QDyning qDyning = QDyning.dyning;

    String keyword = pageRequestDTO.getKeyword();

    // dno > 0 조건만 생성
    BooleanExpression expression = qDyning.dno.gt(0L);
    builder.and(expression);

    // 검색 조건이 없는 경우
    if (type == null || type.trim().length() == 0) {
      return builder;
    }

    // 검색 조건 작성
    BooleanBuilder conditionBuilder = new BooleanBuilder();
    if (type.contains("n")) {
      conditionBuilder.or(qDyning.dyningname.contains(keyword));
    }
    if (type.contains("h")) {
      conditionBuilder.or(qDyning.hashtag.contains(keyword));
    }

    // 모든 조건 통합
    builder.and(conditionBuilder);
    return builder;
  }

}