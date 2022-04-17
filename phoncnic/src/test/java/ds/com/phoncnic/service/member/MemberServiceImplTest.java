package ds.com.phoncnic.service.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.MemberDTO;
import ds.com.phoncnic.dto.pageDTO.PageResultDTO;
import ds.com.phoncnic.dto.pageDTO.SearchMemberPageRequestDTO;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceImplTest {

  @Autowired
  MemberService memberService;

  @Test
  void testAdminSearchPageByMemberId() {

    SearchMemberPageRequestDTO requestDTO = SearchMemberPageRequestDTO.builder()
      .page(1)
      .size(5)
      .type("i")
      .sort("a")
      .keyword("test")
    .build();

    PageResultDTO<MemberDTO, Object[]> pageResult = memberService.adminSearchPageByMemberId(requestDTO);

    System.out.println(pageResult);
    System.out.println(pageResult.getPageList());
  }
}
