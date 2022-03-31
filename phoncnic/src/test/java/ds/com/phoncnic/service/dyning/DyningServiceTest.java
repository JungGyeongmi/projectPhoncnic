package ds.com.phoncnic.service.dyning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchDyningPageRequestDTO;
import ds.com.phoncnic.entity.Dyning;
import ds.com.phoncnic.repository.DyningRepository;

@SpringBootTest
public class DyningServiceTest {

    @Autowired
    DyningService dyningService;

    @Autowired
    DyningRepository dyningRepository;

    /*
     * @Test
     * void testEntitiesToDTO() {
     * PageRequestDTO pageRequestDTO =
     * PageRequestDTO.builder().page(1).size(10).build();
     * 
     * PageResultDTO<DyningDTO, Dyning> pageResultDTO =
     * dyningService.getList(pageRequestDTO);
     * 
     * System.out.println("PREV: " + pageResultDTO.isPrev());
     * System.out.println("NEXT: " + pageResultDTO.isNext());
     * System.out.println("TOAL: " + pageResultDTO.getTotalPage());
     * 
     * for (DyningDTO dyningDTO : pageResultDTO.getDtoList()) {
     * System.out.println(dyningDTO);
     * }
     * 
     * System.out.println("----------------------------------------");
     * pageResultDTO.getPageList().forEach(
     * i -> System.out.printf("%3d", i));
     * 
     * }
     * 
     * @Test
     * public void testSearch() {
     * PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
     * .page(1)
     * .size(10)
     * .type("h") // 검색 조건 name, hashtag
     * .keyword("1") // 검색 키워드
     * .build();
     * 
     * PageResultDTO<DyningDTO, Dyning> resultDTO =
     * dyningService.getList(pageRequestDTO);
     * 
     * System.out.println("PREV: " + resultDTO.isPrev());
     * System.out.println("NEXT: " + resultDTO.isNext());
     * System.out.println("TOTAL: " + resultDTO.getTotalPage());
     * 
     * System.out.println("--------------------------------------------");
     * for (DyningDTO dyningDTO : resultDTO.getDtoList()) {
     * System.out.println(dyningDTO);
     * 
     * System.out.println("============================================");
     * resultDTO.getPageList().forEach(i -> System.out.println(i));
     * }
     * }
     */

    @Test
    public void getDyningPage() {

        SearchDyningPageRequestDTO requestDTO = SearchDyningPageRequestDTO.builder()
                .page(1)
                .size(3)
                .type("n")
                .keyword("1")
                .build();

        PageResultDTO<DyningDTO, Object[]> pageResult = dyningService.getDyningPage(requestDTO);

        System.out.println(pageResult);

    }

    @Test
    public void serchPage() {
        SearchDyningPageRequestDTO requestDTO = SearchDyningPageRequestDTO.builder()
                .page(1)
                .size(3)
                .type("n")
                .keyword("1")
                .build();

        Page<Object[]> result = dyningRepository.searchPage(
            requestDTO.getType(),
            requestDTO.getKeyword(),
            requestDTO.getPageable(Sort.by("dno").descending())
        );

        System.out.println(((Dyning) result.getContent().get(0)[0]).getDno());     
        //    System.out.println(result.getContent().get(0)[1]);
        System.out.println(result.getContent().get(1)[0]);
    }
}
