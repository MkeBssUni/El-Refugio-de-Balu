package com.balu.backend.modules.categories.model;

public interface ICategoryViewPaged {
    Long id();
    String getCategoryName();
    String getCategoryDescription();
    String getCategoryImage();
    Boolean getCategoryStatus();

}
