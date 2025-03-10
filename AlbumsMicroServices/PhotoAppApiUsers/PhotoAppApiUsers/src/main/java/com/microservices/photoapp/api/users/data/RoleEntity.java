package com.microservices.photoapp.api.users.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;


@Entity
@Table(name = "roles")
@Data
@ToString
public class RoleEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy="roles")
    private Collection<UserEntity> users;

    @ToString.Exclude
    @ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinTable(name="roles_authorities", joinColumns=@JoinColumn(name="roles_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="authorities_id", referencedColumnName="id"))
    private Collection<AuthorityEntity> authorities;

    public RoleEntity() {
    }

    public RoleEntity(String name, Collection<AuthorityEntity> authorities) {
        this.name = name;
        this.authorities = authorities;
    }
}

