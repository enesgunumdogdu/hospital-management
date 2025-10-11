package com.enesgunumdogdu.hospital_management.service.impl;

import com.enesgunumdogdu.hospital_management.domain.dto.PrescriptionDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Prescription;
import com.enesgunumdogdu.hospital_management.domain.mapper.PrescriptionMapper;
import com.enesgunumdogdu.hospital_management.repository.PrescriptionRepository;
import com.enesgunumdogdu.hospital_management.service.PrescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, 
                                   PrescriptionMapper prescriptionMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    public List<PrescriptionDto> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();
        return prescriptionMapper.toDtoList(prescriptions);
    }

    @Override
    public PrescriptionDto getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        return prescriptionMapper.toDto(prescription);
    }

    @Override
    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto) {
        Prescription prescription = prescriptionMapper.toEntity(prescriptionDto);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toDto(savedPrescription);
    }

    @Override
    public PrescriptionDto updatePrescription(Long id, PrescriptionDto prescriptionDto) {
        Prescription existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        
        existingPrescription.setMedicationName(prescriptionDto.getMedicationName());
        existingPrescription.setDosage(prescriptionDto.getDosage());
        existingPrescription.setDescription(prescriptionDto.getDescription());
        
        Prescription updatedPrescription = prescriptionRepository.save(existingPrescription);
        return prescriptionMapper.toDto(updatedPrescription);
    }

    @Override
    public void deletePrescription(Long id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new RuntimeException("Prescription not found with id: " + id);
        }
        prescriptionRepository.deleteById(id);
    }
}

