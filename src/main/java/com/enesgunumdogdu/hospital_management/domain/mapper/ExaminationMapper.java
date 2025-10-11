package com.enesgunumdogdu.hospital_management.domain.mapper;

import com.enesgunumdogdu.hospital_management.domain.dto.ExaminationDto;
import com.enesgunumdogdu.hospital_management.domain.entities.Examination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class, PatientMapper.class})
public interface ExaminationMapper {
    
    ExaminationDto toDto(Examination examination);
    
    @Mapping(target = "prescription", ignore = true)
    Examination toEntity(ExaminationDto examinationDto);
    
    List<ExaminationDto> toDtoList(List<Examination> examinations);
}
