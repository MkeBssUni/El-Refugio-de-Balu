package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelDto {
    private String pet;
    private String owner;
    private String cancelReason;
}
