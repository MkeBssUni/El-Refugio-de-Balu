package com.balu.backend.modules.favoritePets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindFavoritePetsDto {
    private String searchValue;
    private String user;
}
