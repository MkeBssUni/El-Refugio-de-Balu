package com.balu.backend.modules.adoptionRequestImage.model;

import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
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
@Table(name = "adoption_request_image")
public class AdoptionRequestImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String image;
    @ManyToOne
    @JoinColumn(name = "adoption_id",referencedColumnName = "id")
    @JsonIgnore
    private AdoptionRequest adoptionRequest;

    public AdoptionRequestImage(String image, AdoptionRequest adoptionRequest) {
        this.image = image;
        this.adoptionRequest = adoptionRequest;
    }
}
