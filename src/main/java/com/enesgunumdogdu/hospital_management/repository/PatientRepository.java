package com.enesgunumdogdu.hospital_management.repository;

import com.enesgunumdogdu.hospital_management.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}

