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

//     @Test
//     public void serchPage() {
//         SearchDyningPageRequestDTO requestDTO = SearchDyningPageRequestDTO.builder()
//             .page(1)
//             .size(3)
//             .type("n")
//             .keyword("1")
//             .build();

//         Page<Object[]> result = dyningRepository.searchPage(
//             requestDTO.getType(),
//             requestDTO.getKeyword(),
//             requestDTO.getPageable(Sort.by("dno").descending())
//         );

//         System.out.println(((Dyning) result.getContent().get(0)[0]).getDno());
//         System.out.println(result.getContent().get(1)[0]);
//     }
// }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        Page<Object[]> page = dyningRepository.getRestaurantStreet(pageRequestDTO.getPageable(Sort.by("dno")));

        page.get().forEach(i->{
            DyningDTO dto = dyningService.roofEntityToDTO((Dyning) i[0]);
            System.out.println(dto);
        });

        // Function<Dyning, Object[]> fn = (entity -> roofEntityToDTO((Dyning) i[0]));
        // new PageResultDTO<>(page, fn);
        
        Function<Object[], DyningDTO> fn = new Function<Object[], DyningDTO>() {
            @Override
            public DyningDTO apply(Object[] en) {
                return dyningService.roofEntityToDTO((Dyning) en[0]);
            }
        };

        PageResultDTO<DyningDTO, Object[]> result = new PageResultDTO<>(page, fn);
        
        System.out.println(result.getPage());
        // Page<Object[]> page = dyningRepository.getRestaurantStreet(pageRequestDTO.getPageable(Sort.by("dno")));
        // page.stream().forEach(System.out::println);
        // System.out.println(page.getTotalElements()); // 17
        // System.out.println(page.get());
        // System.out.println(page.get().getClass());
        // page.get().forEach(i->{
        //     System.out.println(i.toString());
        //     System.out.println(i.length);
        //     System.out.println(i[0]);
        //     System.out.println(i[1]);
        // });



        // Function<Object, Dyning> fn = (obj -> (Dyning)obj);
    }

    @Test
    public void getDyningRestaurantPaging() {
        PageRequestDTO pageRequest = PageRequestDTO.builder().size(10).page(1).build();
        PageResultDTO result = dyningService.getRestaurantPage(pageRequest);
        System.out.println(result.getSize());
        System.out.println(result.getTotalPage());
    }
}
