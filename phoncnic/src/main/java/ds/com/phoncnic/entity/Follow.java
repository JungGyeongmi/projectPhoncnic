package ds.com.phoncnic.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = { "myid", "ceoid", "artistid" })
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;

    // 내 ID
    @ManyToOne(fetch = FetchType.LAZY)
    private Member myid;

    // 내가 팔로우한 가게 (list 형식)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dyning_ceoid")
    private Dyning ceoid;

    // 내가 팔로우한 아티스트들(list 형식)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gallery_artistid")
    private Gallery artistid;
}