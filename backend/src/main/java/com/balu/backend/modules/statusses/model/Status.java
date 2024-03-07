package com.balu.backend.modules.statusses.model;

import com.balu.backend.modules.pets.model.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "statusses")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(40)", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private Statusses name;
    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<Pet> pets;

    public Status(Statusses name) {
        this.name = name;
    }
}
