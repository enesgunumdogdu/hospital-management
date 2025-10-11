package com.enesgunumdogdu.hospital_management.service;

import com.enesgunumdogdu.hospital_management.domain.dto.PrescriptionDto;

import java.util.List;

public interface PrescriptionService {
    
    List<PrescriptionDto> getAllPrescriptions();
    
    PrescriptionDto getPrescriptionById(Long id);
    
    PrescriptionDto createPrescription(PrescriptionDto prescriptionDto);
    
    PrescriptionDto updatePrescription(Long id, PrescriptionDto prescriptionDto);
    
    void deletePrescription(Long id);
}

