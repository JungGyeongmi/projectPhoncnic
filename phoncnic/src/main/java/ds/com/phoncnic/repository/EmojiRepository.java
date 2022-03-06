package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.com.phoncnic.entity.Emoji;

public interface EmojiRepository extends JpaRepository<Emoji,Long>{
    // 인자로 member id 를 받아서 emoji list를 출력하도록 
    // @Query("select e from Emoji e LEFT OUTER JOIN EmojiInfo ei ON e.emojiInfo.emojitype = :ei.emojitype")
    // List<Emoji> getEmojiByEmojiInfo(String emojitype);
}
