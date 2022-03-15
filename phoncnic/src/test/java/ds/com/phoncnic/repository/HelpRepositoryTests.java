package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import ds.com.phoncnic.dto.HelpDTO;
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
        IntStream.rangeClosed(1, 15).forEach(i->{
            int randomMember = (int)(Math.random()*10)+1;  
            int type = (int)(Math.random()*4)+1;
            boolean rand =((int)(Math.random()*2)) !=0;
            Help help = Help.builder()
                .title("제목"+i)
                .content("내용"+i)
                .password("1234")
                .qtype("type"+type)
                .answerstatus(rand)
                .writer(memberRepository.findById("user"+randomMember+"@icloud.com").get())
            .build();
            helpRepository.save(help);
            // 답변용 빈깡통
            Help rehelp = Help.builder()
                .title("호갱님"+i)
                .content("블라블라"+i)
                .password("1234")
                .qtype("답변용"+type)
                .answerstatus(rand)
                // 임의로 10 잡았는데 master user가 필요한건가?
                .writer(memberRepository.findById("user10@icloud.com").get())
            .build();
            helpRepository.save(rehelp);
        });
    }

    @Test
    void testGetListPage() {

        Pageable pageable = PageRequest.of(1, 10);
        
        Page<Help> result = helpRepository.getListPage(pageable);

        System.out.println(result.getSize());
        // System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
        System.out.println(result.hasNext());
        System.out.println(result.getContent());
    }

    @Test
    void registerTest(){
        HelpDTO helpDTO = HelpDTO.builder()
        .title("title")
        .content("content")
        .password("1234")
        .qtype("type1")
        .answerstatus(false)
        .writer("user3@icloud.com")
        .build();

        System.out.println(helpService.register(helpDTO));
    }
}