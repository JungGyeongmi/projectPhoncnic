package ds.com.phoncnic.security.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OAuth2UserDetailsServiceTest {

    @Autowired
    OAuth2UserDetailsService service;
  
    @Test
    public void randomNickNameTest() {
        String str = service.randomNick();
    
        System.out.println(str);
    }

}
