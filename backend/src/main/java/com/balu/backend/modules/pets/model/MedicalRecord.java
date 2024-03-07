package com.balu.backend.modules.pets.model;

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
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private boolean vaccinated;
    @Column(nullable = false)
    private boolean sterilized;
    @Column(nullable = false)
    private boolean dewormed;
    @Column(nullable = false)
    private boolean microchip;
    @Column(nullable = false)
    private String observations;
    @Column(columnDefinition = "json")
    private String diseases;
    @Column(columnDefinition = "json")
    private String allergies;
    @OneToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    @JsonIgnore
    private Pet pet;

}
