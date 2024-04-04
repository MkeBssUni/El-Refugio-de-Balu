package com.balu.backend.modules.adoptionRequests.model.dto;


import com.balu.backend.modules.homeSpecification.model.HomeImage;
import com.balu.backend.modules.homeSpecification.model.HomeSpecification;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveAdoptionRequestDto {
    private String user;
    private String pet;
    private ReasonsForAdoptionDto reasonsForAdoption;
    private PreviousExperiencieDto previousExperiencieDto;
    private String additional_information;
    private String[] imageAdoption;
}
