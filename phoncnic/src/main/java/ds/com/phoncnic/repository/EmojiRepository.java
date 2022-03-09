package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Emoji;

public interface EmojiRepository extends JpaRepository<Emoji,Long>{
    // 인자로 member id 를 받아서 emoji list를 출력하도록
    /* 이모지를 멤버가 마이페이지에서 확인할 때 사용할 것 */
    @Query("select e from Emoji e where e.member.id=:id")
    List<Emoji> getEmojiByMember(String id);

    @Query("select e from Emoji e where e.gallery.gno=:gno")
    List<Emoji> getEmojiByGno(Long gno);
    
    @Query("select e from Emoji e where e.dyning.dno=:dno")
    List<Emoji> getEmojiByDno(Long dno);
    
    // @Query("select e, count(e) from Emoji e where e.gallery.gno=:gno group by e.gallery.gno ")
    // List<Emoji> getCountEmoji(Long gno);

    // @Query("select e, count(e) from Emoji e where e.emojiinfo.emojitype=:emojitype group by e.gallery.gno ")
    // List<Emoji> getCountEmoji(String emojitype);


//     @Query("select e, count(e) from Emoji e group by e.eno ")
//     List<Emoji> getCountEmoji();

//     SELECT gallery_gno, emoji_info_emojitype, 
// COUNT( emoji_info_emojitype) FROM emoji where gallery_gno =11 GROUP BY emoji_info_emojitype ;


// @Query("select e, count(e.emojitype) from Emoji e where e.gno =:gno group by e.emojitype ")
// List<Emoji> getCountEmoji(Long gno);

@Query("select e.emojiInfo.emojitype, count(e.emojiInfo.emojitype) from Emoji e where e.gallery.gno = :gno group by e.emojiInfo.emojitype")
    List<Object[]> getEmojiCountByGno(Long gno);

// @Query("select e, count(e.emojitype) from Emoji e group by gno ")
    // List<Emoji> getCountEmoji(Long gno);

}
