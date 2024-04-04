package com.balu.backend.modules.homeSpecification.model.Repository;

import com.balu.backend.modules.homeSpecification.model.HomeSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeSpecificationRepository extends JpaRepository<HomeSpecification,Long> {

}
