package com.balu.backend.modules.homeSpecification.model;


import com.balu.backend.modules.adresses.model.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "home_specifications")
public class HomeSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(40)")
    @Enumerated(EnumType.STRING)
    private HomeTypes type;
    @Column(columnDefinition ="BOOLEAN")
    private boolean outdoorArea;
    private int numberOfResidents;
    @OneToOne(mappedBy = "homeSpecification")
    @JsonIgnore
    private Address address;
    @OneToOne(mappedBy = "homeSpecification")
    @JsonIgnore
    private HomeImage homeImage;
}
