package com.balu.backend.modules.categories.model;

public interface ICategoryViewPaged {
    Long getId();
    String getCategoryName();
    String getCategoryDescription();
    String getCategoryImage();
    Boolean getCategoryStatus();
}
