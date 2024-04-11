package com.balu.backend.modules.people.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicRegisterDto {
    private String name;
    private String lastname;
    private String surname;
    private String phoneNumber;
    private String username;
    private String password;
    private boolean viaSms;
}
