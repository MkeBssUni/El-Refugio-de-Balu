package com.balu.backend.modules.pets.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPetRepository extends JpaRepository<Pet,Long> {
    @Query(value = "select * from pets", nativeQuery = true)
    List<Pet> findAllPaged(Long categoryId, String size, String lifeStage, String gender, String location, Pageable pageable);
}
