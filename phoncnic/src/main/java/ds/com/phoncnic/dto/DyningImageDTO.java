package ds.com.phoncnic.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DyningImageDTO {
    
    private Long dno;
    
    private String menuimagename;
    private String menuimageuuid;
    private String menuimagepath;

    private String backgroundname;
    private String backgrounduuid;
    private String backgroundpath;

    public String getMenuImageURL() {
        try {
            return URLEncoder.encode(menuimagepath+"/"+menuimageuuid+"_"+menuimagename, "UTF-8");

        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public String getMenuThumbnailImageURL() {
        try {
            return URLEncoder.encode(menuimagepath+"/s_"+menuimageuuid+"_"+menuimagename, "UTF-8");

        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getBackgoundImageURL() {
        try {
            return URLEncoder.encode(backgroundpath+"/"+backgrounduuid+"_"+backgroundname, "UTF-8");

        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getBackgoundThumbnailImageURL() {
        try {
            return URLEncoder.encode(backgroundpath+"/s_"+backgrounduuid+"_"+backgroundname, "UTF-8");

        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String id;

}
