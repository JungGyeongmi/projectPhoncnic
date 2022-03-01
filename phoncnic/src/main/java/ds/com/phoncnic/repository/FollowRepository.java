package ds.com.phoncnic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.com.phoncnic.entity.Follow;

public interface FollowRepository  extends JpaRepository<Follow,Long> {
    
}
