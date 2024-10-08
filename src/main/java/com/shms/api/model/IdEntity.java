package com.shms.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id"})
@MappedSuperclass
public abstract class IdEntity  {

  @Id
  @GeneratedValue(generator = "system-uuid", strategy = GenerationType.IDENTITY)
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "id", nullable = false, updatable = false)
  protected String id;

  public IdEntity(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof IdEntity)) return false;

    IdEntity that = (IdEntity) o;

    return this.id != null && Objects.equals(this.id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
