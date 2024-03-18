package com.balu.backend.modules.categories.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryListDto {
    private String id;
    private String name;

    public GetCategoryListDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

}
