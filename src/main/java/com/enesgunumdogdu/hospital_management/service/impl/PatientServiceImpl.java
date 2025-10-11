package com.enesgunumdogdu.hospital_management.service.impl;

import com.enesgunumdogdu.hospital_management.domain.dto.PatientDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Patient;
import com.enesgunumdogdu.hospital_management.domain.mapper.PatientMapper;
import com.enesgunumdogdu.hospital_management.repository.PatientRepository;
import com.enesgunumdogdu.hospital_management.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toDtoList(patients);
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        return patientMapper.toDto(patient);
    }

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = patientMapper.toEntity(patientDto);
        Patient savedPatient = patientRepository.save(patient);
        return patientMapper.toDto(savedPatient);
    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));
        
        existingPatient.setFirstNameAndLastName(patientDto.getFirstNameAndLastName());
        existingPatient.setIdentityNumber(patientDto.getIdentityNumber());
        existingPatient.setBirthDate(patientDto.getBirthDate());
        existingPatient.setPhoneNumber(patientDto.getPhoneNumber());
        
        Patient updatedPatient = patientRepository.save(existingPatient);
        return patientMapper.toDto(updatedPatient);
    }

    @Override
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new RuntimeException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}

