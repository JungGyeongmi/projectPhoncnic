package ds.com.phoncnic.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import ds.com.phoncnic.entity.Qna;

@SpringBootTest
public class QnaRepositoryTests {


    @Autowired
    QnaRepository qnaRepository;

    @Autowired
    MemberRepository memberRepository;


    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 10).forEach(i->{
            int randomMember = (int)(Math.random()*10)+1;  
            int type = (int)(Math.random()*4)+1;
            boolean rand =((int)(Math.random()*2)) !=0;
            Qna qna = Qna.builder()
                .title("제목"+i)
                .content("내용"+i)
                .password("1234")
                .qtype("type"+type)
                .answerstatus(rand)
                .writer(memberRepository.findById("user"+randomMember+"@icloud.com").get())
            .build();
            qnaRepository.save(qna);
        });
    }

    @Test
    void testGetListPage() {

        Pageable pageable = PageRequest.of(0, 2);
        
        Page<Qna> result = qnaRepository.getListPage(pageable);

        System.out.println(result.getSize());
        // System.out.println(result.getTotalElements());
        System.out.println(result.getTotalPages());
        System.out.println(result.hasNext());
        System.out.println(result.getContent());
    }
}
