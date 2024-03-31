package com.balu.backend.modules.categories.model.dto;

import com.balu.backend.modules.categories.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SaveCategoryDto {
    private String name;
    private String description;
    private String image;
}
