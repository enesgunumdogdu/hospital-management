package com.enesgunumdogdu.hospital_management.domain.dto;

import java.time.LocalDateTime;

public class ExaminationDto {
    private Long id;
    private DoctorDto doctor;
    private PatientDto patient;
    private LocalDateTime examinationDate;
    private String diagnosis;

    public ExaminationDto() {}

    public ExaminationDto(Long id, DoctorDto doctor, PatientDto patient, LocalDateTime examinationDate, String diagnosis) {
        this.id = id;
        this.doctor = doctor;
        this.patient = patient;
        this.examinationDate = examinationDate;
        this.diagnosis = diagnosis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DoctorDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDto doctor) {
        this.doctor = doctor;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public LocalDateTime getExaminationDate() {
        return examinationDate;
    }

    public void setExaminationDate(LocalDateTime examinationDate) {
        this.examinationDate = examinationDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
