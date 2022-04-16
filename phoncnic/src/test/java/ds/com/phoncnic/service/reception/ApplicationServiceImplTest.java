package ds.com.phoncnic.service.reception;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ds.com.phoncnic.dto.ApplicationFormDTO;
import ds.com.phoncnic.entity.ApplicationForm;
import ds.com.phoncnic.entity.ApplicationImage;
import ds.com.phoncnic.entity.Member;
import ds.com.phoncnic.repository.ApplicationFormRepository;
import ds.com.phoncnic.repository.ApplicationImageRepository;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ApplicationServiceImplTest {
  
  @Autowired
  ApplicationFormService service;

  @Autowired
  ApplicationFormRepository repository;
  
  @Autowired
  ApplicationImageRepository imgrepository;

  @Test
  public void regiTestFianl() {
    ApplicationFormDTO applicationDTO = ApplicationFormDTO.builder()
      .applicationtype(false)
      .content("잘부탁드려용")
      .dyningname("웅맴")
      .applicant("gm950715@gmail.com")
    .build();
    Long afno = service.register(applicationDTO);
    System.out.println(afno);
  }

  @Test
  public void testRegister() {
    ApplicationFormDTO applicationDTO = ApplicationFormDTO.builder()
      .applicationtype(false)
      .content("잘부탁드려용")
      .dyningname("웅맴")
      .applicant("test1@gmail.com")
    .build();

    Map<String, Object> entityMap = service.dtoToEntity(applicationDTO);

    System.out.println(entityMap.get("applicationform"));
    System.out.println(entityMap.get("businessregistration"));
  }

  @Test
  public void repositorySaveTest() {
    ApplicationForm app = ApplicationForm.builder()
      .applicationtype(false)
      .content("잘부탁함둥")
      .dyningname("가게이름")
      .member(Member.builder().id("test1@gmail.com").build())
    .build();
    repository.save(app);
    
    ApplicationImage img = ApplicationImage.builder()
      .imagename("테스트자격증명서")
      .imagepath("2022\\04\\01")
      .uuid("uuid.uu")
      .applicationform(app)
    .build();
    imgrepository.save(img);
  }
}
