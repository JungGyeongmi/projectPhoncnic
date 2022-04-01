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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchDyningPageRequestDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.entity.EmojiInfo;
import ds.com.phoncnic.entity.RoofDesign;
import ds.com.phoncnic.repository.DyningImageRepository;
import ds.com.phoncnic.repository.DyningRepository;
import ds.com.phoncnic.repository.RoofDesignRepository;
import ds.com.phoncnic.service.emoji.EmojiInfoService;
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
  
  @Autowired
  private final EmojiInfoService emojiInfoService;

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
  public List<RoofDesign> roofimageList() {
    List<RoofDesign> dto = roofDesignRepository.findAll();
    return dto;
  }

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
    List<DyningImage> dyningImageList = imagesDTOToEntity(dyningDTO);

    if (!dyningImageList.isEmpty()) {
      dyningImageList.forEach(dyningImage -> {
        dyningImageRepository.save(dyningImage);
      });
    }

    RoofDesign roof = roofDesignRepository.findById(dyningDTO.getOono()).get();

    dyning.modifyDyning(dyningDTO.getDyningname(), dyningDTO.getComment(), dyningDTO.getLocation(),
        dyningDTO.getLocationdetails(), dyningDTO.getFoodtype(),
        dyningDTO.getBusinesshours(), dyningDTO.getTel(), dyningDTO.getHashtag(), roof);

    dyningRepository.save(dyning);
  }

  @Override
  public DyningDTO getDyningDetails(Long dno) {

    List<Object[]> result = dyningRepository.getDyningDetails(dno);

    Dyning dyninglist = (Dyning) result.get(0)[0];
    Long emojiCwt = (Long) result.get(0)[1];
    
    Long followerCwt = dyningRepository.getDyningFollowerCount(dno);
    List<DyningImage> dyningImageList = dyningRepository.getImageDetailsPage(dno);

    List<EmojiInfo> emojiInfoList = emojiInfoService.getEmojiInfoList();

    return entityToDTO(dyninglist, emojiCwt, dyningImageList, followerCwt, emojiInfoList);
  }

  @Override
  public PageResultDTO<DyningDTO, Object[]> getDyningPage(SearchDyningPageRequestDTO searchPageRequestDTO) {
    log.info("searchRequestDTO ..." + searchPageRequestDTO);

    Function<Object[], DyningDTO> fn = new Function<Object[], DyningDTO>() {
      @Override
      public DyningDTO apply(Object[] en) {
        return entitiesToDTO((Dyning) en[0]);
      }
    };

    Page<Object[]> result = dyningRepository.searchPage(
        searchPageRequestDTO.getType(),
        searchPageRequestDTO.getKeyword(),
        searchPageRequestDTO.getPageable(Sort.by("dno").descending()));

    return new PageResultDTO<>(result, fn);
  }
}