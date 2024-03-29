package com.balu.backend.modules.adresses.model.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateAddressDto {
    private Long id;
    private String country;
    private String street;
    private String colony;
    private String city;
    private String state;
    private String postalCode;
    private String addressReference;
    private String exteriorNumber;
    private String interiorNumber;
    private Long userId;
    private Long homeSpecificationId;
}
