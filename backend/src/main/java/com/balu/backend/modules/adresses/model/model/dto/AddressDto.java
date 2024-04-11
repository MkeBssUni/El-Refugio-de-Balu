package com.balu.backend.modules.adresses.model.model.dto;

import com.balu.backend.modules.homeSpecification.model.Dto.HomeSpecificationDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private String addressId;
    private String country;
    private String street;
    private String colony;
    private String city;
    private String state;
    private String postalCode;
    private String addressReference;
    private String exteriorNumber;
    private String interiorNumber;
    private HomeSpecificationDto homeSpecification;
}
