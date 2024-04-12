package com.balu.backend.modules.people.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePersonalInfoDto {
    private String userId;
    private String name;
    private String surname;
    private String lastname;
    private String email;
    private String phone;
}
