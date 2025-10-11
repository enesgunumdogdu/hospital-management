package com.enesgunumdogdu.hospital_management.domain.dto;

public class PrescriptionDto {
    private Long id;
    private ExaminationDto examination;
    private String medicationName;
    private String dosage;
    private String description;

    public PrescriptionDto() {}

    public PrescriptionDto(Long id, ExaminationDto examination, String medicationName, String dosage, String description) {
        this.id = id;
        this.examination = examination;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExaminationDto getExamination() {
        return examination;
    }

    public void setExamination(ExaminationDto examination) {
        this.examination = examination;
    }
}
