package com.balu.backend.modules.adoptionRequests.model;

import com.balu.backend.modules.adoptionRequestImage.model.AdoptionRequestImage;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.users.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "adoption_requests")
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "username"})
    private User user;
    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    @JsonIncludeProperties({"id", "username"})
    private Pet pet;

    @OneToMany(mappedBy = "adoptionRequest")
    @JsonIncludeProperties({"id", "image"})
    private List<AdoptionRequestImage> requestImages;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
    @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Column(columnDefinition = "json",nullable = false)
    private String reasons_for_adoption;
    @Column(columnDefinition = "json",nullable = false)
    private String previous_experience;
    @Column(nullable = false)
    private String additional_information;

    public AdoptionRequest(User user, Pet pet,  Status status, String reasons_for_adoption, String previous_experience, String additional_information) {
        this.user = user;
        this.pet = pet;
        this.status = status;
        this.reasons_for_adoption = reasons_for_adoption;
        this.previous_experience = previous_experience;
        this.additional_information = additional_information;
    }

}
