package com.enesgunumdogdu.hospital_management.service;

import com.enesgunumdogdu.hospital_management.domain.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    
    List<DoctorDto> getAllDoctors();
    
    DoctorDto getDoctorById(Long id);
    
    DoctorDto createDoctor(DoctorDto doctorDto);
    
    DoctorDto updateDoctor(Long id, DoctorDto doctorDto);
    
    void deleteDoctor(Long id);
}
