package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

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

    private String size;

    private String description;

    private String[] characteristics;

    private String[] specialCares;

    private String mainImage;

    private String[] images;

    private boolean vaccinated;

    private boolean sterilized;

    private boolean dewormed;

    private boolean microchip;

    private String observations;

    private String[] diseases;

    private String[] allergies;

    private String category;

    private String owner;
}
