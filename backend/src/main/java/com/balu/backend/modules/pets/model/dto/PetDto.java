package com.balu.backend.modules.pets.model.dto;

import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.users.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PetDto {
    private String name;

    private String gender;

    private String breed;

    private int age;

    private String ageUnit;

    private String lifeStage;

    private Double weight;

    private String weightUnit;

    private String description;

    private String[] characteristics;

    private String[] specialCares;

    private String mainImage;

    private List<String> images;

    private boolean vaccinated;

    private boolean sterilized;

    private boolean dewormed;

    private boolean microchip;

    private String observations;

    private String[] diseases;

    private String[] allergies;

    private Category category;

    private User owner;
}
