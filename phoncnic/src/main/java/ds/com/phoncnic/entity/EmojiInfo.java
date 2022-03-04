package ds.com.phoncnic.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmojiInfo {
    @Id
    private String emojitype;
    private String emojipath;
    private String kindofemoji;
}
