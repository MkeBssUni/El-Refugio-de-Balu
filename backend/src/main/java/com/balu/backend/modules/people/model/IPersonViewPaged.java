package com.balu.backend.modules.people.model;

public interface IPersonViewPaged {
    Long getId();
    String getFullname();
    String getPhone();
    String getUsername();
    boolean getBlocked();
    String getRole();
}
