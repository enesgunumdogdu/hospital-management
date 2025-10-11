package com.enesgunumdogdu.hospital_management.service;

import com.enesgunumdogdu.hospital_management.domain.dto.PatientDto;

import java.util.List;

public interface PatientService {
    
    List<PatientDto> getAllPatients();
    
    PatientDto getPatientById(Long id);
    
    PatientDto createPatient(PatientDto patientDto);
    
    PatientDto updatePatient(Long id, PatientDto patientDto);
    
    void deletePatient(Long id);
}

