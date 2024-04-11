package com.balu.backend.modules.homeSpecification.model.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateHomeSpecificationDto {
    private String idUser;
    private String specificationId;
    private String type;
    private String mainImage;
    private boolean outdoorArea;
    private int numberOfResidents;
}
