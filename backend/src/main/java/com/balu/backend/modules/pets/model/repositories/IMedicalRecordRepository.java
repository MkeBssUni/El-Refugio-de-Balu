package com.balu.backend.modules.pets.model.repositories;

import com.balu.backend.modules.pets.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {

}
