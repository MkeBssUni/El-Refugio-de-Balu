package com.balu.backend.modules.adoptionRequests.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAdoptionRequestDto {
    private String idUser;
    private String searchValue;
}
