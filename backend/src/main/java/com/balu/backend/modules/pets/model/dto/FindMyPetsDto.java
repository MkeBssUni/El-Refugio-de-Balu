package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMyPetsDto {
    private String user;
    private String status;
    private String searchValue;
}
