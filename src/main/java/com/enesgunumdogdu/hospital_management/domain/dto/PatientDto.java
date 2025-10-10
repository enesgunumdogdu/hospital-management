package com.enesgunumdogdu.hospital_management.domain.dto;

import java.time.LocalDate;

public class PatientDto {
    private Long id;
    private String firstNameAndLastName;
    private String identityNumber;
    private LocalDate birthDate;
    private String phoneNumber;

    public PatientDto() {}

    public PatientDto(Long id, String firstNameAndLastName, String identityNumber, LocalDate birthDate, String phoneNumber) {
        this.id = id;
        this.firstNameAndLastName = firstNameAndLastName;
        this.identityNumber = identityNumber;
        this.birthDate = birthDate;
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

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
