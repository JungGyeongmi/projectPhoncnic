package ds.com.phoncnic.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
@ToString
public class EmojiInfo {
    @Id
    private String emojitype;
    private String emojipath;
    private String kindofemoji;

    @OneToOne(fetch = FetchType.LAZY)
    private Emoji emoji;
}
