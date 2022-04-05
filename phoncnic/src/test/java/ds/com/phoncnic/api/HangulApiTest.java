package ds.com.phoncnic.api;

import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class HangulApiTest {

    
    @Test
    void testAPI() {
        String test = "";
        try {
            List<String> list = HangulApi.hangul("1", 17L);
            log.info(HangulApi.hangul("1", 17L));
            test = list.get(0);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        log.info(test);
    }

}
