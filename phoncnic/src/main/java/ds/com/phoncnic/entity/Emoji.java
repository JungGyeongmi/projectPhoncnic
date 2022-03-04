package ds.com.phoncnic.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
@ToString(exclude = {"member", "dyning", "gallery", "emojiInfo"})
public class Emoji extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eno;

    private String emojitype;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dyning dyning;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gallery gallery;

    
    @OneToOne(mappedBy = "emoji", fetch = FetchType.LAZY)
    @JoinColumn(name = "emojitype")
    private EmojiInfo emojiInfo;

}
