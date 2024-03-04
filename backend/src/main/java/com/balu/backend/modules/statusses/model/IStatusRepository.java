package com.balu.backend.modules.statusses.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStatusRepository extends JpaRepository<Status,Long> {
    Optional<Status> findByName(Statusses name);
}
