package com.balu.backend.modules.adoptionRequests.model;

import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.users.model.User;
import jakarta.persistence.*;
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
@Table(name = "adoption_requests")
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;
    @Column(columnDefinition = "json", nullable = false)
    private String images;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;
    @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
