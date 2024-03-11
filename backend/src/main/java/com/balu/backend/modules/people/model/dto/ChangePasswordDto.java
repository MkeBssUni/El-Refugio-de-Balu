package com.balu.backend.modules.people.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {
    private Long personId;
    private String oldPassword;
    private String newPassword;
}
