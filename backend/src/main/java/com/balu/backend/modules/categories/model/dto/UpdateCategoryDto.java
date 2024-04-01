package com.balu.backend.modules.categories.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UpdateCategoryDto {
    private String id;
    private String name;
    private String description;
    private String image;
    private String userId;
}
