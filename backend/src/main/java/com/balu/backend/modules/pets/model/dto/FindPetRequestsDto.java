package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindPetRequestsDto {
    private String category;
    private String searchValue;
}
