package com.enesgunumdogdu.hospital_management.domain.mapper;

import com.enesgunumdogdu.hospital_management.domain.dto.PrescriptionDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Prescription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {
    
    @Mapping(source = "examination.id", target = "examinationId")
    PrescriptionDto toDto(Prescription prescription);
    
    @Mapping(target = "examination", ignore = true)
    Prescription toEntity(PrescriptionDto prescriptionDto);
    
    List<PrescriptionDto> toDtoList(List<Prescription> prescriptions);
}
