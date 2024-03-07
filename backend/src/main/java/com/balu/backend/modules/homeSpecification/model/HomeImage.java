package com.balu.backend.modules.homeSpecification.model;

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
}
