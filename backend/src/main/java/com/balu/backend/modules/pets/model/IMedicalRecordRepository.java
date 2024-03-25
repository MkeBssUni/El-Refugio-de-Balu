package com.balu.backend.modules.pets.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {

}
