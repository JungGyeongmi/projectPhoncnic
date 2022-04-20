package ds.com.phoncnic.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Getter
public abstract class BaseRegiDate {
  @CreatedDate
  @Column(name="applydate", updatable=false)
  private LocalDateTime applyDate;

  @LastModifiedDate
  @Column(name="confrimdate")
  private LocalDateTime confirmDate;
}