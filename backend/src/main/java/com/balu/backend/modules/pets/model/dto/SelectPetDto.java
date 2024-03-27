package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SelectPetDto {
    private String pet;
    private String user;
    private String status;
}
