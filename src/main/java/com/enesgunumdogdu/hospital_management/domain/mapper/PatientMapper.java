package com.enesgunumdogdu.hospital_management.domain.mapper;

import com.enesgunumdogdu.hospital_management.domain.dto.PatientDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Patient;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    
    PatientDto toDto(Patient patient);
    
    Patient toEntity(PatientDto patientDto);
    
    List<PatientDto> toDtoList(List<Patient> patients);
}
