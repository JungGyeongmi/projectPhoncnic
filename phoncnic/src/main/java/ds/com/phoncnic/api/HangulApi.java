package ds.com.phoncnic.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HangulApi {
    public static List<String> hangul(String keyword, Long type) throws IOException, ParseException {

        // 1. URL을 만들기위한 StringBuilder
        StringBuilder urlBuilder = new StringBuilder("http://opendict.korean.go.kr/api/search");
      
        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키
        urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=2F74BAB4FC5A41DDC2DF2A2BF680C565");
        urlBuilder.append("&" + URLEncoder.encode("q", "UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("req_type", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("part", "UTF-8") + "=" + URLEncoder.encode("word", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pos", "UTF-8") + "=" + type); // 명사 17, 동사5, 형용사6


        // 3. URL 객체 생성
        URL url = new URL(urlBuilder.toString());

        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 5. 통신을 위한 메소드 SET.
        conn.setRequestMethod("GET");

        // 6. 통신을 위한 Content-type SET.
        conn.setRequestProperty("Content-type", "application/json");

        // 7. 통신 응답 코드 확인.
        System.out.println("Response code: " + conn.getResponseCode());

        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            // log.info(line);
            sb.append(line.trim());
        }

        // 10. 객체 해제.
        rd.close();
        conn.disconnect();

        List<String> resultList = new ArrayList<>();

        // Json parse
        // 1. 문자열 형태의 JSON을 파싱하기 위한 JSONParser 객체 생성.
        JSONParser parser = new JSONParser();
        // 2. 문자열을 JSON 형태로 JSONObject 객체에 저장. 	
        JSONObject obj = (JSONObject)parser.parse(sb.toString());
        obj = (JSONObject) obj.get("channel");
        // log.info(obj.get("item").getClass());
        JSONArray jsonarr = (JSONArray) obj.get("item");
        for (int i = 0; i<jsonarr.size(); i++ ){
            obj =  (JSONObject) jsonarr.get(i);
            String newWord = (String)obj.get("word");
            newWord = newWord.replace("^", "").replace(" ", "");
            resultList.add(newWord);
        }

        // 11. 전달받은 데이터 확인.
        // System.out.println(sb.toString());
        return resultList;
    }
}
