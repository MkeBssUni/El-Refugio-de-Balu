package com.balu.backend.modules.adoptionRequests.model.dto;


import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.users.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveAdoptionRequest {
    private User user;
    private Pet pet;
    private String images;
    private Status status;
    private String reasons_for_adoption;
    private String previous_experience;
    private String additional_information;
}
