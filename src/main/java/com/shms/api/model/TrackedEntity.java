package com.shms.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@MappedSuperclass
public abstract class TrackedEntity extends IdEntity {
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @Column(name = "created", updatable = false)
  @CreationTimestamp
  protected Date createdAt;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @Column(name = "modified")
  @UpdateTimestamp
  protected Date lastModified;
}
