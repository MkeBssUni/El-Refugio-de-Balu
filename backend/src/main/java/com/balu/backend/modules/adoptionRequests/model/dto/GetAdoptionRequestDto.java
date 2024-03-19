package com.balu.backend.modules.adoptionRequests.model.dto;

import com.balu.backend.modules.pets.model.Pet;
import com.balu.backend.modules.statusses.model.Status;
import com.balu.backend.modules.users.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAdoptionRequestDto {
    private long id;
    private User user;
    private Pet pet;
    private String images;
    private Status status;
    private LocalDateTime createdAt;
    private String reasons_for_adoption;
    private String previous_experience;
    private String additional_information;
}
