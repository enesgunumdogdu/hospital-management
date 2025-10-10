package com.enesgunumdogdu.hospital_management.domain.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstNameAndLastName;
    private String specialty;
    private String phoneNumber;

    public Doctor() {}

    public Doctor(Long id, String firstNameAndLastName, String specialty, String phoneNumber) {
        this.id = id;
        this.firstNameAndLastName = firstNameAndLastName;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameAndLastName() {
        return firstNameAndLastName;
    }

    public void setFirstNameAndLastName(String firstNameAndLastName) {
        this.firstNameAndLastName = firstNameAndLastName;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
