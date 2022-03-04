package ds.com.phoncnic.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoofDesignInfo {
    
    @Id
    private Long rooftype;

    private String roofpath;
    private Long kindofroop;
}
