package com.balu.backend.modules.adoptionRequests.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReasonsForAdoptionDto {
    private String haveHadPets;
    private String whereWillThePetBe;
    private String peopleAgreeToAdopt;
    private String whyAdoptPet;
}
