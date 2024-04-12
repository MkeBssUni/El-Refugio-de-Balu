package com.balu.backend.modules.adresses.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface IAddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByUserId(Long userId);

    @Modifying
    @Query(value = "UPDATE addresses SET home_specification_id = :idHome WHERE id = :id", nativeQuery = true)
    Integer changeHomeSpeceficationAssign(@Param("id") Long id, @Param("idHome") Long idHome);



}

