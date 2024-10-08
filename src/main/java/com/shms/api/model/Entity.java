package com.shms.api.model;

//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@NoArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Entity implements Serializable {

  @Column(name = "deleted")
  protected Date deletedAt;

  public boolean isDeleted() {
    return !Objects.isNull(deletedAt);
  }

  public boolean isNotDeleted() {
    return Objects.isNull(deletedAt);
  }

  public void delete() {
    deletedAt = new Date(System.currentTimeMillis());
  }

  public void restore() {
    deletedAt = null;
  }
}