package com.balu.backend.modules.categories.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveCategoryDto {
    private String name;
    private String description;
    private String image;
    private String userId;
}
