package com.balu.backend.modules.pets.model.views;

public interface IPetsByUserView {
    Long getId();
    String getImage();
    String getName();
    String getLocation();
    Long getFavorite();
}
