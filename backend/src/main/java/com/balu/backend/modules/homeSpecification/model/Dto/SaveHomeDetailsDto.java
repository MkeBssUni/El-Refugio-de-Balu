package com.balu.backend.modules.homeSpecification.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaveHomeDetailsDto {
    private String idUser;
    private String type;
    private boolean outdoorArea;
    private int numberOfResidents;
    private String[] homeImages;
}
