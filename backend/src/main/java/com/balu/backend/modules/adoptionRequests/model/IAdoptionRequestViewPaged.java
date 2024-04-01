package com.balu.backend.modules.adoptionRequests.model;

public interface IAdoptionRequestViewPaged {
    Long getId();
    String getCreated_at();
    Long getPet_id();
    String getPetName();
    String getSpecie();
    String getStatus();
}
