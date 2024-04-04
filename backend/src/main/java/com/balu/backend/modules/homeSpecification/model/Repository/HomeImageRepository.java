package com.balu.backend.modules.homeSpecification.model.Repository;

import com.balu.backend.modules.homeSpecification.model.HomeImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeImageRepository extends JpaRepository<HomeImage,Long> {
}
