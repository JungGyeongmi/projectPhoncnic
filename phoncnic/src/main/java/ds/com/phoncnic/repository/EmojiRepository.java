package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.Emoji;

public interface EmojiRepository extends JpaRepository<Emoji,Long>{
    // 인자로 member id 를 받아서 emoji list를 출력하도록
    /* 이모지를 멤버가 마이페이지에서 확인할 때 사용할 것 */
    @Query("select e from Emoji e where e.member.id=:id")
    Emoji getEmojiByMember(String id);
}
