package com.balu.backend.modules.pets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyPetsCatalog {
    String id;
    String image;
    int comments;
    String name;
    String location;
    String status;
}
