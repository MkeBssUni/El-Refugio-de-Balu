package com.balu.backend.modules.users.model;

import com.balu.backend.modules.adoptionRequests.model.AdoptionRequest;
import com.balu.backend.modules.adresses.model.Address;
import com.balu.backend.modules.favoritePets.model.FavoritePet;
import com.balu.backend.modules.people.model.Person;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.roles.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text", nullable = false, unique = true)
    private String username;
    @Column(columnDefinition = "text", nullable = false)
    @JsonIgnore
    private String password;
    private int attempts;

    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastAccess;
    @Column(columnDefinition = "BOOL default false")
    private boolean blocked;
    @Column(columnDefinition = "BOOL default false")
    private boolean profileCompleted;
    @Column(columnDefinition = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime blockedAt;
    private String activationCode;
    @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Person person;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Address address;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Pet> ownedPets;
    @OneToMany(mappedBy = "moderator")
    @JsonIgnore
    private List<Pet> moderatedPets;
    @OneToMany(mappedBy = "adoptant")
    @JsonIgnore
    private List<Pet> adoptedPets;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<AdoptionRequest> adoptionRequests;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<FavoritePet> favoritePets;

    public User(String username, String password, Role role1) {
        this.username = username;
        this.password = password;
        this.role = role1;
    }
    public User(String username, String password, Role role1, boolean profileCompleted) {
        this.username = username;
        this.password = password;
        this.role = role1;
        this.profileCompleted = profileCompleted;
    }
    public void save(String username, String password, Role role, String recoveryCode){
        this.username = username;
        this.password = password;
        this.role = role;
        this.activationCode = recoveryCode;
        this.blocked = true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", attempts=" + attempts +
                ", lastAccess=" + lastAccess +
                ", blocked=" + blocked +
                ", blockedAt=" + blockedAt +
                ", createdAt=" + createdAt +
                ", role=" + role +
                ", person=" + person +
                ", address=" + address +
                '}';
    }
}
