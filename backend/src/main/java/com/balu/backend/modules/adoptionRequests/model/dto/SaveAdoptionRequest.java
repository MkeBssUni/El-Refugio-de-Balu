package com.balu.backend.modules.adoptionRequests.model.dto;


import com.balu.backend.modules.homeSpecification.model.HomeImage;
import com.balu.backend.modules.homeSpecification.model.HomeSpecification;
import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.users.model.User;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveAdoptionRequest {
    private User user;
    private Pet pet;
    private String place_of_residence;
    private Status status;
    private String reasons_for_adoption;
    private String previous_experience;
    private String additional_information;
    private HomeImage homeImage;
    private HomeSpecification homeSpecification;
}
