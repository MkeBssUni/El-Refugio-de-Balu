package com.balu.backend.modules.auth.dto;

import com.balu.backend.modules.roles.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignedDto {
    private String token;
    private String tokenType;
    private String userId;
    private Role role;
    private boolean profileCompleted;

}
