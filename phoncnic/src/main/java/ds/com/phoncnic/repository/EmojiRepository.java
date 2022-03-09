package ds.com.phoncnic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

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
    
}
