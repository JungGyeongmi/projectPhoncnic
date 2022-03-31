package ds.com.phoncnic.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoofDesign extends BaseEntity {
//     다이닝- dno pk, 멤버 fk, 푸드타입 =1, 루프 이미지 패스
// 루프디자인 - oono pk, 루프네임, 루프타입 (=푸드타입) =1, 한식루프 이미지 패스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oono;

    private Long rooftype; //foodtype
    private String roofname;
    private String roofthumbnail;
    private String roofpath;

}
