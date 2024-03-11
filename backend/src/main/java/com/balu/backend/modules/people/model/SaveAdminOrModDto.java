package com.balu.backend.modules.people.model;

import com.balu.backend.modules.roles.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveAdminOrModDto {
    private String encripted_user_id;
    private String name;
    private String lastname;
    private String surname;
    private String phoneNumber;
    private String username;
    private String password;
    private Role role;
}
