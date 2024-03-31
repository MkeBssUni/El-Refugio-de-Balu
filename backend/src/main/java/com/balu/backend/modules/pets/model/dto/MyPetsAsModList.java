package com.balu.backend.modules.pets.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MyPetsAsModList {
    private String id;
    private int requests;
    private String category;
    private String name;
    private String status;
}
