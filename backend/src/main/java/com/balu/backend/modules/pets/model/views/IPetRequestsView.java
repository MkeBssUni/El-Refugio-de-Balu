package com.balu.backend.modules.pets.model.views;

public interface IPetRequestsView {
    Long getId();
    String getCategory();
    String getName();
    String getBreed();
    String getSize();
    String getGender();
    int getAge();
    String getAgeUnit();
    String getStatus();
}
