package com.balu.backend.modules.categories.model.dto;

import com.balu.backend.modules.categories.model.Category;
import com.balu.backend.modules.pets.model.Pet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class GetCategoryListDto {
    private Long id;
    private String name;

}
