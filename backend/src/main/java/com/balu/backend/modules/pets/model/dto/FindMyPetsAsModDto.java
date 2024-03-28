package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMyPetsAsModDto {
    private String user;
    private String category;
    private String status;
    private String searchValue;
}
