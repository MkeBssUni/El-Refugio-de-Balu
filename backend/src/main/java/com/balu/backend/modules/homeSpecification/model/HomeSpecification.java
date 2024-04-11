package com.balu.backend.modules.homeSpecification.model;


import com.balu.backend.modules.adresses.model.model.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
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

    public HomeSpecification(HomeTypes type, boolean outdoorArea, int numberOfResidents) {
        this.type = type;
        this.outdoorArea = outdoorArea;
        this.numberOfResidents = numberOfResidents;
    }


    public HomeSpecification(HomeTypes homeType, boolean outdoorArea, int numberOfResidents, Address address) {
        this.type = homeType;
        this.outdoorArea = outdoorArea;
        this.numberOfResidents = numberOfResidents;
        this.address = address;
    }
}
