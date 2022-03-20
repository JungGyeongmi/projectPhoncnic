package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
    @Query("delete from Emoji e where e.gallery.gno=:gno")
    void deleteByGno(Long gno);

    @Modifying
    @Query("delete from Emoji e where e.dyning.dno=:dno")
    void deleteByDno(Long dno);

    
    @Query("select e from Emoji e where e.dyning.dno=:dno")
    List<Emoji> findByDno(Long dno);

    @Query("select e from Emoji e where e.member.id=:id")
    List<Emoji> findByMemberId(String id);



    @Modifying
    @Query("delete from Emoji where eno=:eno")
    void deleteByEno(Long eno);


    //게시물별
    @Query("select e, count(e) from Emoji e where e.emojiInfo.emojitype= :type group by e.eno ")
    List<Emoji> getCountEmoji(String type);

    //타입별

    @Query("select e.emojiInfo.emojitype, count(e.emojiInfo.emojitype) from Emoji e where e.gallery.gno = :gno group by e.emojiInfo.emojitype")
    List<Object[]> getEmojiCountByGno(Long gno);

    // @Query("select e.emojiInfo.emojitype, count(e.emojiInfo.emojitype) from Emoji e where e.gallery.gno = :gno group by e.emojiInfo.emojitype")
    // List<Object[]> getEmojiCountByGno(Long gno)

}
