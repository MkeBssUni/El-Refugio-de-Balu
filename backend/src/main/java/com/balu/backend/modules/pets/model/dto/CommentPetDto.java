package com.balu.backend.modules.pets.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentPetDto {
    private String pet;
    private String user;
    private String comment;
}
