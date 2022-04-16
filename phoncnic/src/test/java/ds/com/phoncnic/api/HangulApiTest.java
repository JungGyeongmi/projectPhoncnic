package ds.com.phoncnic.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

public class HangulApiTest {

  @Test
  void testHangul() {
    
    List<String>  list = new ArrayList<>();
    HangulApi hangul = new HangulApi();
    
    try {
      list = HangulApi.hangul("아시아", 26L);
    } catch (IOException | ParseException | NullPointerException e) {
      e.printStackTrace();
    }

    list.stream().forEach(System.out::println);
  }
}
