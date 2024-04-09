package com.balu.backend.modules.homeSpecification.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "home_images")
public class HomeImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String image;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="home_specification_id", referencedColumnName = "id")
    @JsonIgnore
    private HomeSpecification homeSpecification;

    public HomeImage(String image, HomeSpecification homeSpecification) {
        this.image = image;
        this.homeSpecification = homeSpecification;
    }
}
