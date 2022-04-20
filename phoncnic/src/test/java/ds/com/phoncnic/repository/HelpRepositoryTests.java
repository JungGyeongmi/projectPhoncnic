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
            String[] qtype = {"계정정보", "사장/작가 등록", "이용 문의", "에러 신고"};

            int type = (int)(Math.random()*4);
            boolean rand =((int)(Math.random()*2)) !=0;
            Help help = Help.builder()
                .title("질문"+i)
                .content("질문내용"+i)
                .password("1234")
                .qtype(qtype[type])
                .answerstatus(rand)
                .writeremail("test"+randomMember+"@gmail.com")
            .build();
            helpRepository.save(help);
        });
    }

}
