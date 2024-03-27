package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndPetRequestDto {
    private String pet;
    private String user;
}
