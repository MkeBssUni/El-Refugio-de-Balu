package com.balu.backend.modules.people.model;


import com.balu.backend.modules.people.model.dto.PublicRegisterDto;
import com.balu.backend.modules.people.model.dto.SaveAdminOrModDto;
import com.balu.backend.modules.users.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    private String surName;
    @Column(nullable = false)
    private String phoneNumber;
    private String secondaryPhoneNumber;
    private String secondaryEmail;
    @Column(columnDefinition = "LONGTEXT")
    private String profilePicture;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public void savePublicRegister(PublicRegisterDto dto, User user){
        this.name = dto.getName();
        this.lastName = dto.getLastname();
        this.surName = dto.getSurname();
        this.phoneNumber = dto.getPhoneNumber();
        this.user = user;
    }
    public void saveAdminOrMod(SaveAdminOrModDto dto, User user){
        this.name = dto.getName();
        this.lastName = dto.getLastname();
        this.surName = dto.getSurname();
        this.phoneNumber = dto.getPhoneNumber();
        this.user = user;
    }
}
