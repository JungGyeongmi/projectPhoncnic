package ds.com.phoncnic.service.dyning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchDyningPageRequestDTO;
import ds.com.phoncnic.repository.DyningRepository;

@SpringBootTest
public class DyningServiceTest {

    @Autowired
    DyningService dyningService;

    @Autowired
    DyningRepository dyningRepository;

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
}
