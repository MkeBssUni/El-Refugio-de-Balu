package com.balu.backend.modules.favoritePets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FavoritePetsCatalog {
    private String id;
    private String petId;
    private String name;
    private String image;
    private String location;
}
