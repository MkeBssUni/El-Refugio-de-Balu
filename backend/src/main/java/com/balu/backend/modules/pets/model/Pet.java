package com.balu.backend.modules.pets.model;

import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.favoritePets.model.FavoritePet;
import com.balu.backend.modules.pets.model.enums.*;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genders gender;
    @Column(nullable = false, columnDefinition = "varchar(50)")
    private String breed;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AgeUnits ageUnit;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LifeStages lifeStage;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WeightUnits weightUnit;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sizes size;
    @Column(columnDefinition = "varchar(1500)", nullable = false)
    private String description;
    @Column(columnDefinition = "text")
    private String specialCares;
    @Column(columnDefinition = "text", nullable = false)
    private String characteristics;
    @Column(columnDefinition = "text", nullable = false)
    private String mainImage;
    @Column(columnDefinition = "varchar(500)")
    private String cancelReason;
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<PetImage> petImages;
    @OneToOne(mappedBy = "pet")
    private MedicalRecord medicalRecord;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "name"})
    private Category category;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "name"})
    private Status status;
    @ManyToOne
    @JoinColumn(name = "user_owner_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id"})
    private User owner;
    @ManyToOne
    @JoinColumn(name = "user_moderator_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id"})
    private User moderator;
    @ManyToOne
    @JoinColumn(name = "user_adoptant_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id"})
    private User adoptant;
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<AdoptionRequest> adoptionRequests;
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<FavoritePet> favoritePets;

    public Pet(String name, String gender, String breed, int age, String ageUnit, String lifeStage, Double weight, String weightUnit, String size, String description, String characteristics, String specialCares, String mainImage, Category category, User user, Status status) {
        this.name = name;
        this.gender = Genders.valueOf(gender.toUpperCase());
        this.breed = breed;
        this.age = age;
        this.ageUnit = AgeUnits.valueOf(ageUnit.toUpperCase());
        this.lifeStage = LifeStages.valueOf(lifeStage.toUpperCase());
        this.weight = weight;
        this.weightUnit = WeightUnits.valueOf(weightUnit.toUpperCase());
        this.size = Sizes.valueOf(size.toUpperCase());
        this.description = description;
        this.characteristics = characteristics;
        this.specialCares = specialCares;
        this.mainImage = mainImage;
        this.category = category;
        this.owner = user;
        this.status = status;
    }

    public void update(String name, String gender, String breed, int age, String ageUnit, String lifeStage, Double weight, String weightUnit, String size, String description, String characteristics, String specialCares, String mainImage, Category category) {
        this.name = name;
        this.gender = Genders.valueOf(gender.toUpperCase());
        this.breed = breed;
        this.age = age;
        this.ageUnit = AgeUnits.valueOf(ageUnit.toUpperCase());
        this.lifeStage = LifeStages.valueOf(lifeStage.toUpperCase());
        this.weight = weight;
        this.weightUnit = WeightUnits.valueOf(weightUnit.toUpperCase());
        this.size = Sizes.valueOf(size.toUpperCase());
        this.description = description;
        this.characteristics = characteristics;
        this.specialCares = specialCares;
        this.mainImage = mainImage;
        this.category = category;
    }
}
