package com.balu.backend.modules.favoritePets.model;

import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.users.model.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "favorite_pets")
public class FavoritePet {
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
    @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime addedAt;

    public FavoritePet(User user, Pet pet) {
        this.user = user;
        this.pet = pet;
    }
}
