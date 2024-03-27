package com.balu.backend.modules.pets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PetRequestList {
    private String id;
    private String category;
    private String name;
    private String breed;
    private String size;
    private String gender;
    private int age;
    private String ageUnit;
    private String status;
}
