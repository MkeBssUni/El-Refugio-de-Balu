package com.balu.backend.modules.pets.model;

import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.favoritePets.model.FavoritePet;
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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genders gender;
    @Column(nullable = false)
    private String breed;
    @Column(nullable = false)
    private Double age;
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
    @Column(columnDefinition = "varchar(500)", nullable = false)
    private String description;
    @Column(columnDefinition = "text", nullable = false)
    private String characteristics;
    @Column(columnDefinition = "text", nullable = false)
    private String mainImage;
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<PetImages> petImages;
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
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<AdoptionRequest> adoptionRequests;
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<FavoritePet> favoritePets;
}
