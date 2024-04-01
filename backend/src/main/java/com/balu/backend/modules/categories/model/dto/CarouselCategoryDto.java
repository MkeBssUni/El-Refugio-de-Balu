package com.balu.backend.modules.categories.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarouselCategoryDto {
    private String id;
    private String name;
    private String description;
    private String image;
}
