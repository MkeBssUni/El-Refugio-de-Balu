package com.balu.backend.modules.adresses.model.model;

public interface IAddressViewPaged {
    Long getId();
    String getCountry();
    String getStreet();
    String getColony();
    String getCity();
    String getState();
    String getPostalCode();
    String getAddressReference();
    String getExteriorNumber();
    String getInteriorNumber();
    Long getUserId();
    Long getHomeSpecificationId();
}
