package ds.com.phoncnic.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationImageDTO {
  
  private String imagename;
  private String imagepath;
  private String uuid;

  
  public String getImageURL() {
    try {
        return URLEncoder.encode(imagepath+"/"+uuid+"_"+imagename, "UTF-8");

    } catch(UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return "";
}

}
