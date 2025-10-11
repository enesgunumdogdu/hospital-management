package com.enesgunumdogdu.hospital_management.service.impl;

import com.enesgunumdogdu.hospital_management.domain.dto.ExaminationDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Doctor;
import com.enesgunumdogdu.hospital_management.domain.entities.Examination;
import com.enesgunumdogdu.hospital_management.domain.entities.Patient;
import com.enesgunumdogdu.hospital_management.domain.mapper.ExaminationMapper;
import com.enesgunumdogdu.hospital_management.repository.DoctorRepository;
import com.enesgunumdogdu.hospital_management.repository.ExaminationRepository;
import com.enesgunumdogdu.hospital_management.repository.PatientRepository;
import com.enesgunumdogdu.hospital_management.service.ExaminationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;
    private final ExaminationMapper examinationMapper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public ExaminationServiceImpl(ExaminationRepository examinationRepository, 
                                  ExaminationMapper examinationMapper,
                                  DoctorRepository doctorRepository,
                                  PatientRepository patientRepository) {
        this.examinationRepository = examinationRepository;
        this.examinationMapper = examinationMapper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<ExaminationDto> getAllExaminations() {
        List<Examination> examinations = examinationRepository.findAll();
        return examinationMapper.toDtoList(examinations);
    }

    @Override
    public ExaminationDto getExaminationById(Long id) {
        Examination examination = examinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examination not found with id: " + id));
        return examinationMapper.toDto(examination);
    }

    @Override
    public ExaminationDto createExamination(ExaminationDto examinationDto) {
        Doctor doctor = doctorRepository.findById(examinationDto.getDoctor().getId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        Patient patient = patientRepository.findById(examinationDto.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        Examination examination = new Examination();
        examination.setDoctor(doctor);
        examination.setPatient(patient);
        examination.setExaminationDate(examinationDto.getExaminationDate());
        examination.setDiagnosis(examinationDto.getDiagnosis());
        
        Examination savedExamination = examinationRepository.save(examination);
        return examinationMapper.toDto(savedExamination);
    }

    @Override
    public ExaminationDto updateExamination(Long id, ExaminationDto examinationDto) {
        Examination existingExamination = examinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examination not found with id: " + id));
        
        Doctor doctor = doctorRepository.findById(examinationDto.getDoctor().getId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        Patient patient = patientRepository.findById(examinationDto.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        
        existingExamination.setDoctor(doctor);
        existingExamination.setPatient(patient);
        existingExamination.setExaminationDate(examinationDto.getExaminationDate());
        existingExamination.setDiagnosis(examinationDto.getDiagnosis());
        
        Examination updatedExamination = examinationRepository.save(existingExamination);
        return examinationMapper.toDto(updatedExamination);
    }

    @Override
    public void deleteExamination(Long id) {
        if (!examinationRepository.existsById(id)) {
            throw new RuntimeException("Examination not found with id: " + id);
        }
        examinationRepository.deleteById(id);
    }
}

