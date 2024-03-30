package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompleteAdoptionDto {
    private String pet;
    private String adoptant;
    private String moderator;
}
