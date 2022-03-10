package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.com.phoncnic.entity.EmojiInfo;

public interface EmojiInfoRepository extends JpaRepository<EmojiInfo, String> {
    
}
