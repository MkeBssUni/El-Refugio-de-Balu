package com.balu.backend.modules.favoritePets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RemoveFavoritePetDto {
    private String favoritePet;
    private String user;
}
