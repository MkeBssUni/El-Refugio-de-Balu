package com.balu.backend.modules.pets.model;

import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.pets.model.enums.AgeUnits;
import com.balu.backend.modules.pets.model.enums.Genders;
import com.balu.backend.modules.pets.model.enums.LifeStages;
import com.balu.backend.modules.pets.model.enums.WeightUnits;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genders gender;
    @Column(nullable = false)
    private Double age;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeUnits ageUnit;
    @Column(nullable = false)
    private String breed;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WeightUnits weightUnit;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LifeStages lifeStage;
    @Column(columnDefinition = "text", nullable = false)
    private String description;
    @Column(columnDefinition = "json", nullable = false)
    private String characteristics;
    @Column(columnDefinition = "text", nullable = false)
    private String mainImage;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "name"})
    private Category category;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "name"})
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id"})
    private User user;
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<PetImages> petImages;
    @OneToOne(mappedBy = "pet")
    private MedicalRecord medicalRecord;
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<AdoptionRequest> adoptionRequests;
}
