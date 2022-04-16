package ds.com.phoncnic.service.dyning;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import ds.com.phoncnic.dto.DyningDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
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
