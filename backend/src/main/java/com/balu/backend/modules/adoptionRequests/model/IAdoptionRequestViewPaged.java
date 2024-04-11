package com.balu.backend.modules.adoptionRequests.model;

public interface IAdoptionRequestViewPaged {
    Long getId();
    String getCreatedAt();
    Long getPetId();
    String getPetName();
    String getSpecie();
    String getStatus();
}
