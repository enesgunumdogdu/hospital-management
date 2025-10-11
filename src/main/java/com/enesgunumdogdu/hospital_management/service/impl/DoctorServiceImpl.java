package com.enesgunumdogdu.hospital_management.service.impl;

import com.enesgunumdogdu.hospital_management.domain.dto.DoctorDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Doctor;
import com.enesgunumdogdu.hospital_management.domain.mapper.DoctorMapper;
import com.enesgunumdogdu.hospital_management.repository.DoctorRepository;
import com.enesgunumdogdu.hospital_management.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.toDtoList(doctors);
    }

    @Override
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        return doctorMapper.toDto(doctor);
    }

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Doctor doctor = doctorMapper.toEntity(doctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return doctorMapper.toDto(savedDoctor);
    }

    @Override
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        
        existingDoctor.setFirstNameAndLastName(doctorDto.getFirstNameAndLastName());
        existingDoctor.setSpecialty(doctorDto.getSpecialty());
        existingDoctor.setPhoneNumber(doctorDto.getPhoneNumber());
        
        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return doctorMapper.toDto(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }
}
