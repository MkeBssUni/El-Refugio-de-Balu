package com.balu.backend.modules.statusses.model;

import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.pets.model.Pet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @OneToMany(mappedBy = "status")
    @JsonIgnore
    private List<AdoptionRequest> adoptionRequests;

    public Status(Statusses name) {
        this.name = name;
    }
}
