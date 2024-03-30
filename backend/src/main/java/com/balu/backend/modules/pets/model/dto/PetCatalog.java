package com.balu.backend.modules.pets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PetCatalog {
    private String id;
    private String name;
    private String location;
    private boolean isFavorite;
}
