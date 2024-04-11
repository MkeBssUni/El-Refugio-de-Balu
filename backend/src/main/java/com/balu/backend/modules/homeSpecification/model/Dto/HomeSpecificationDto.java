package com.balu.backend.modules.homeSpecification.model.Dto;

import com.balu.backend.modules.homeSpecification.model.HomeTypes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeSpecificationDto {
    private String homeSpecificationId;
    private HomeTypes type;
    private boolean outdoorArea;
    private int numberOfResidents;
    private String homeImage;
}
