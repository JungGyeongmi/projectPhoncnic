package ds.com.phoncnic.service.dyning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.PageRequestDTO;
import ds.com.phoncnic.dto.PageResultDTO;
import ds.com.phoncnic.entity.Dyning;

@SpringBootTest
public class DyningServiceTest {

    @Autowired
    DyningService dyningService;

    @Test
    void testEntitiesToDTO() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<DyningDTO, Dyning> pageResultDTO = dyningService.getList(pageRequestDTO);

        System.out.println("PREV: " + pageResultDTO.isPrev());
        System.out.println("NEXT: " + pageResultDTO.isNext());
        System.out.println("TOAL: " + pageResultDTO.getTotalPage());

        for (DyningDTO dyningDTO : pageResultDTO.getDtoList()) {
            System.out.println(dyningDTO);
        }

        System.out.println("----------------------------------------");
        pageResultDTO.getPageList().forEach(
                i -> System.out.printf("%3d", i));

    }

    @Test
    public void testSearch() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("h") // 검색 조건 t, c, w, tc, tcw ...
                .keyword("1") // 검색 키워드
                .build();

        PageResultDTO<DyningDTO, Dyning> resultDTO = dyningService.getList(pageRequestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL: " + resultDTO.getTotalPage());

        System.out.println("--------------------------------------------");
        for (DyningDTO dyningDTO : resultDTO.getDtoList()) {
            System.out.println(dyningDTO);

            System.out.println("============================================");
            resultDTO.getPageList().forEach(i -> System.out.println(i));
        }
    }
}
