package com.enesgunumdogdu.hospital_management.service.impl;

import com.enesgunumdogdu.hospital_management.domain.dto.PrescriptionDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Examination;
import com.enesgunumdogdu.hospital_management.domain.entities.Prescription;
import com.enesgunumdogdu.hospital_management.domain.mapper.PrescriptionMapper;
import com.enesgunumdogdu.hospital_management.repository.ExaminationRepository;
import com.enesgunumdogdu.hospital_management.repository.PrescriptionRepository;
import com.enesgunumdogdu.hospital_management.service.PrescriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final ExaminationRepository examinationRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, 
                                   PrescriptionMapper prescriptionMapper,
                                   ExaminationRepository examinationRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionMapper = prescriptionMapper;
        this.examinationRepository = examinationRepository;
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
        Examination examination = examinationRepository.findById(prescriptionDto.getExaminationId())
                .orElseThrow(() -> new RuntimeException("Examination not found with id: " + prescriptionDto.getExaminationId()));
        
        Prescription prescription = prescriptionMapper.toEntity(prescriptionDto);
        prescription.setExamination(examination);
        
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return prescriptionMapper.toDto(savedPrescription);
    }

    @Override
    public PrescriptionDto updatePrescription(Long id, PrescriptionDto prescriptionDto) {
        Prescription existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + id));
        
        Examination examination = examinationRepository.findById(prescriptionDto.getExaminationId())
                .orElseThrow(() -> new RuntimeException("Examination not found with id: " + prescriptionDto.getExaminationId()));
        
        existingPrescription.setExamination(examination);
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

