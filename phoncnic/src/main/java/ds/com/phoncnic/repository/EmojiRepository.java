package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Emoji;

public interface EmojiRepository extends JpaRepository<Emoji, Long> {
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

    // 게시물별
    @Query("select e, count(e) from Emoji e where e.emojiInfo.emojitype= :type group by e.eno ")
    List<Emoji> getCountEmoji(String type);

    // 타입별
    @Query("SELECT e.emojiInfo.emojitype, COUNT(e) FROM Emoji e " +
            "WHERE e.gallery.gno IS NOT NULL AND e.gallery.gno = :gno " +
            "GROUP BY e.gallery.gno, e.emojiInfo.emojitype ORDER BY 1, 2 ")
    List<Object[]> getEmojiCountByGno(Long gno);

    @Query("SELECT e FROM Emoji e WHERE e.member.id=:id AND e.dyning.dno=:dno")
    Emoji getEnoAndType(String id, Long dno);

    //다이닝 이모지 타입 개수 카운트
    @Query("select count(e.emojiInfo.emojitype) from Emoji e where e.dyning.dno=:dno and e.emojiInfo.emojitype=:emojitype")
    Long getEmojiCountByEmojitype(Long dno, String emojitype);

    @Query("select e.emojiInfo.emojitype,count(e.emojiInfo.emojitype) from Emoji e where e.dyning.dno=:dno and e.emojiInfo.emojitype=:emojitype")
    List<Object[]> getEmojiCountByEmojitype2(Long dno, String emojitype);

    /*
     * update를 사용할때는 modifying annotation
     * update 테이블 set 컬럼 = 변경된컬럼값 where 조건 = :조건 and 조건 = :조건
     */
    @Modifying
    @Query("UPDATE Emoji e SET e.emojiInfo.emojitype = :type WHERE e.gallery.gno = :gno AND e.member.id = :id")
    Integer updateEmojiTypeByGnoAndMemberId(String type, Long gno, String id);

    @Modifying
    @Query("SELECT e.eno, e.emojiInfo.emojitype, COUNT(e.member.id) > 0 FROM Emoji e WHERE e.gallery.gno = :gno AND e.member.id = :id")
    List<Object[]> existsByMemberIdANDGno(Long gno, String id);
}
