package com.enesgunumdogdu.hospital_management.domain.mapper;

import com.enesgunumdogdu.hospital_management.domain.dto.DoctorDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Doctor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    
    DoctorDto toDto(Doctor doctor);
    
    Doctor toEntity(DoctorDto doctorDto);
    
    List<DoctorDto> toDtoList(List<Doctor> doctors);
}
