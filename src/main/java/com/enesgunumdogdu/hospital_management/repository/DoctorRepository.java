package com.enesgunumdogdu.hospital_management.repository;

import com.enesgunumdogdu.hospital_management.domain.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
