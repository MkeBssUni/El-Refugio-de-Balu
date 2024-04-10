package com.balu.backend.modules.adoptionRequests.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveAdoptionRequestDto {
    private String user;
    private String pet;
    private ReasonsForAdoptionDto reasonsForAdoption;
    private PreviousExperiencieDto previousExperiencieDto;
    private String additionalInformation;
    private String[] imageAdoption;


}
