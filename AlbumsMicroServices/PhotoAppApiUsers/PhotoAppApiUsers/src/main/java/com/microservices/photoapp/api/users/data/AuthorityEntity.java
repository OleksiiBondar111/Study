package com.microservices.photoapp.api.users.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "authorities")
@Data
@ToString
public class AuthorityEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "authorities")
    private Collection<RoleEntity> roles;

    public AuthorityEntity() {
    }

    public AuthorityEntity(String name) {
        this.name = name;
    }
}
