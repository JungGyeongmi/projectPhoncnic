package ds.com.phoncnic.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GalleryDTO {
    
    private Long gno;
    private String title;
    private String  content;
    
    private String imagename;
    private boolean imagetype;
    private String imagepath;
    private String uuid;

    private Long[][] emojicount;

    private HashMap<String, String> emojiinfo;

    //Member id
    private String id;

    private LocalDateTime regdate;
    private LocalDateTime moddate;

    public String getImageURL() {
        try {
            return URLEncoder.encode(imagepath+"/"+uuid+"_"+imagename, "UTF-8");

        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL() {
        try {
            return URLEncoder.encode(imagepath+"/s_"+uuid+"_"+imagename, "UTF-8");

        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}