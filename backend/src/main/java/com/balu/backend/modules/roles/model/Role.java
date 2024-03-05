package com.balu.backend.modules.roles.model;

import com.balu.backend.modules.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(40)", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Roles name;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users;

    public Role(Roles name) {
        this.name = name;
    }
}
