package com.balu.backend.modules.people.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivateAccountDto {
    private String username;
    private String activationCode;
}
