package com.balu.backend.modules.adresses.model.model.dto;

import com.balu.backend.modules.users.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveAddressDto {
    private String country;
    private String street;
    private String colony;
    private String city;
    private String state;
    private String postalCode;
    private String addressReference;
    private String exteriorNumber;
    private String interiorNumber;
    private String userId;
    private User user;
}
