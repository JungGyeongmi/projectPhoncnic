package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.EmojiInfo;

public interface EmojiInfoRepository extends JpaRepository<EmojiInfo, String> {
    
    @Query("select ei from EmojiInfo ei")
    List<EmojiInfo> getEmojiInfoAll();

}
