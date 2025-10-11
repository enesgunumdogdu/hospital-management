package com.enesgunumdogdu.hospital_management.service;

import com.enesgunumdogdu.hospital_management.domain.dto.ExaminationDto;

import java.util.List;

public interface ExaminationService {
    
    List<ExaminationDto> getAllExaminations();
    
    ExaminationDto getExaminationById(Long id);
    
    ExaminationDto createExamination(ExaminationDto examinationDto);
    
    ExaminationDto updateExamination(Long id, ExaminationDto examinationDto);
    
    void deleteExamination(Long id);
}

