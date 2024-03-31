package com.balu.backend.modules.pets.model.views;

public interface IMyPetsAsModView {
    Long getId();
    int getCancelRequest();
    int getRequests();
    String getCategory();
    String getName();
    String getStatus();
}
