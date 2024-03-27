package com.balu.backend.modules.pets.model.views;

import com.balu.backend.modules.pets.model.enums.Genders;

public interface IPetCredentialView {
    String getName();
    String getBreed();
    String getCategory();
    String getWeight();
    String getSize();
    String getLifeStage();
    String getAge();
    String getGender();
}
