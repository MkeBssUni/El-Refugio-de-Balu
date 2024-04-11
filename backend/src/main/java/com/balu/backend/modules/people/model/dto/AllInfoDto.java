package com.balu.backend.modules.people.model.dto;

import com.balu.backend.modules.adresses.model.model.dto.AddressDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllInfoDto {
    private String username;
    private String name;
    private String lastname;
    private String surname;
    private String phoneNumber;
    private AddressDto addressDto;
}
