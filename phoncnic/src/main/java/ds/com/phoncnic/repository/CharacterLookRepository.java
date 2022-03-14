package ds.com.phoncnic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.com.phoncnic.entity.CharacterLook;

public interface CharacterLookRepository extends JpaRepository<CharacterLook,Long>{
    @Query("select c from CharacterLook c where c.member.id = :id")
    Optional<CharacterLook> getLnoById(String id);
}
