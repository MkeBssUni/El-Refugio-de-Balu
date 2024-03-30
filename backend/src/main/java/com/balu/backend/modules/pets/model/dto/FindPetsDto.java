package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindPetsDto {
    private String user;
    private String category;
    private String size;
    private String lifeStage;
    private String gender;
    private String location;
}
