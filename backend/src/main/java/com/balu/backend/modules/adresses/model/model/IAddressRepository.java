package com.balu.backend.modules.adresses.model.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByUserId(Long userId);

}

