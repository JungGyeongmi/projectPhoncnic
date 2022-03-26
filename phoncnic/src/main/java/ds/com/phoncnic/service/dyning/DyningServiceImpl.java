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
import org.springframework.stereotype.Service;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.entity.DyningImage;
import ds.com.phoncnic.entity.QDyning;
import ds.com.phoncnic.repository.DyningImageRepository;
import ds.com.phoncnic.repository.DyningRepository;
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
  public List<DyningDTO> getStreet() {
    List<Dyning> result = dyningRepository.getStreetList();
    List<DyningDTO> DyningList = result.stream().map(entity -> roofEntityToDTO(entity)).collect(Collectors.toList());
    return DyningList;
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
  public PageResultDTO<DyningDTO, Dyning> getList(PageRequestDTO pageRequestDTO) {

    Pageable pageable = pageRequestDTO.getPageable(Sort.by("dno"));

    BooleanBuilder builder = getSearch(pageRequestDTO);

    Page<Dyning> result = dyningRepository.findAll(builder, pageable);

    Function<Dyning, DyningDTO> fn = (entity -> entitiesToDTO(entity));
    return new PageResultDTO<>(result, fn);
  }

  private BooleanBuilder getSearch(PageRequestDTO pageRequestDTO) {
    String type = pageRequestDTO.getType();

    BooleanBuilder builder = new BooleanBuilder();

    QDyning qDyning = QDyning.dyning;

    String keyword = pageRequestDTO.getKeyword();

    BooleanExpression expression = qDyning.dno.gt(0L);
    builder.and(expression);

    if (type == null || type.trim().length() == 0) {
      return builder;
    }

    BooleanBuilder conditionBuilder = new BooleanBuilder();
    if (type.contains("n")) {
      conditionBuilder.or(qDyning.dyningname.contains(keyword));
    }
    if (type.contains("h")) {
      conditionBuilder.or(qDyning.hashtag.contains(keyword));
    }

    builder.and(conditionBuilder);
    return builder;
  }

}