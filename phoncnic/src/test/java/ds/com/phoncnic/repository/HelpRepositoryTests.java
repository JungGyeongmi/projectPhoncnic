package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import ds.com.phoncnic.dto.HelpDTO;
import ds.com.phoncnic.dto.pageDTO.PageRequestDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.entity.Help;
import ds.com.phoncnic.service.help.HelpService;


@SpringBootTest
public class HelpRepositoryTests {


    @Autowired
    HelpRepository helpRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    HelpService helpService;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 30).forEach(i->{
            int randomMember = (int)(Math.random()*10)+1;  
            int type = (int)(Math.random()*4)+1;
            boolean rand =((int)(Math.random()*2)) !=0;
            Help help = Help.builder()
                .title("질문"+i)
                .content("질문내용"+i)
                .password("1234")
                .qtype("type"+type)
                .answerstatus(rand)
                .writeremail("test"+randomMember+"@gmail.com")
            .build();
            helpRepository.save(help);
        });
    }

    @Test
    void testGetListPage() {

        Pageable pageable = PageRequest.of(1, 10);
        
        Page<Help> result = helpRepository.getListPage(pageable);

        System.out.println(result.getSize());
        System.out.println(result.getTotalPages());
        System.out.println(result.hasNext());
        System.out.println(result.getContent());
    }

    @Test
    void registerTest(){
        HelpDTO helpDTO = HelpDTO.builder()
            .title("title!")
            .content("content")
            .password("1234")
            .qtype("type1")
            .answerstatus(false)
            .writeremail("test3@gmail.com")
        .build();

        System.out.println(helpService.register(helpDTO));
    }

    @Test
    void getPageListTest() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<HelpDTO, Object[]> page = helpService.getQnaList(pageRequestDTO);
        System.out.println(page);
        System.out.println(page.getPage());
        System.out.println(page.getTotalPage());
    }

   @Test
    void removeByQno() {
        helpService.remove(2L);
    }
}
