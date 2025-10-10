package com.enesgunumdogdu.hospital_management.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "examinations")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private LocalDateTime examinationDate;
    private String diagnosis;

    @OneToOne(mappedBy = "examination", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Prescription prescription;

    public Examination() {}

    public Examination(Long id, Doctor doctor, Patient patient, LocalDateTime examinationDate, String diagnosis) {
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
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

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
