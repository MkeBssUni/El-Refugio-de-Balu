package com.balu.backend.modules.adresses.model;

import com.balu.backend.modules.adresses.model.dto.AddressDto;
import com.balu.backend.modules.adresses.model.dto.SaveAddressDto;
import com.balu.backend.modules.homeSpecification.model.HomeSpecification;
import com.balu.backend.modules.users.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String street;
    private String colony;
    private String city;
    private String state;
    private String postalCode;
    @Column(columnDefinition = "text")
    private String addressReference;
    private String exteriorNumber;
    private String interiorNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="home_specification_id", referencedColumnName = "id")
    private HomeSpecification homeSpecification;

    public void save (SaveAddressDto dto){
        country = dto.getCountry();
        street = dto.getStreet();
        colony = dto.getColony();
        city = dto.getCity();
        state= dto.getState();
        postalCode = dto.getPostalCode();
        addressReference = dto.getAddressReference();
        exteriorNumber = dto.getExteriorNumber();
        interiorNumber = dto.getInteriorNumber();
        user=dto.getUser();
    }
    public void saveNull(User user){
        this.user = user;
    }
    public void saveFullAddres (AddressDto dto){
        country = dto.getCountry();
        street = dto.getStreet();
        colony = dto.getColony();
        city = dto.getCity();
        state= dto.getState();
        postalCode = dto.getPostalCode();
        addressReference = dto.getAddressReference();
        exteriorNumber = dto.getExteriorNumber();
        interiorNumber = dto.getInteriorNumber();
    }
}
