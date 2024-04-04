package com.balu.backend.modules.pets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PetDetails {
    private String mainImage;
    private String[] images;
    private String name;
    private String location;
    private String gender;
    private String category;
    private String breed;
    private String size;
    private String lifeStage;
    private int age;
    private String ageUnit;
    private double weight;
    private String weightUnit;
    private String[] characteristics;
    private String description;
    private boolean vaccinated;
    private boolean dewormed;
    private boolean sterilized;
    private boolean microchip;
    private String[] diseases;
    private String[] allergies;
    private String observations;
    private String[] specialCares;
    private String status;
}
