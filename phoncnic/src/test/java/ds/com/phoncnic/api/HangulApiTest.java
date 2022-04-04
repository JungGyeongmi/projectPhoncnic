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
        try {
            log.info(HangulApi.hangul("1", 17L));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
