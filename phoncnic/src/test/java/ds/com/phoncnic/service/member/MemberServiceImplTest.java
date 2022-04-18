package ds.com.phoncnic.service.member;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchMemberPageRequestDTO;
import ds.com.phoncnic.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceImplTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Transactional
  @Test
  void testAdminSearchPageByMemberId() {

    SearchMemberPageRequestDTO requestDTO = SearchMemberPageRequestDTO.builder()
      .page(3)
      .size(5)
      .type("n")
      .sort("r")
      .keyword("동")
    .build();

    PageResultDTO<MemberDTO, Object[]> pageResult = memberService.adminSearchPageByMemberId(requestDTO);

    System.out.println("result "+pageResult.getTotalPage());
    System.out.println("result "+pageResult.getPageList());
    System.out.println("result "+pageResult.getPage());
    System.out.println("result "+pageResult.getDtoList());
  }
}
