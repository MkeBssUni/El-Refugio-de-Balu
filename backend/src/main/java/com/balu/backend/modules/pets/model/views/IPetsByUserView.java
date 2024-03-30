package com.balu.backend.modules.pets.model.views;

public interface IPetsByUserView {
    Long getId();
    String getName();
    String getLocation();
    boolean getIsFavorite();
}
