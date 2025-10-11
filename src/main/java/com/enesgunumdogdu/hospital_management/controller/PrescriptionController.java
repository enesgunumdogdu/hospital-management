package com.enesgunumdogdu.hospital_management.controller;

import com.enesgunumdogdu.hospital_management.domain.dto.PrescriptionDto;
import com.enesgunumdogdu.hospital_management.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionDto>> getAllPrescriptions() {
        List<PrescriptionDto> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDto> getPrescriptionById(@PathVariable Long id) {
        PrescriptionDto prescriptionDto = prescriptionService.getPrescriptionById(id);
        return ResponseEntity.ok(prescriptionDto);
    }

    @PostMapping
    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        PrescriptionDto createdPrescriptionDto = prescriptionService.createPrescription(prescriptionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrescriptionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDto> updatePrescription(@PathVariable Long id, @RequestBody PrescriptionDto prescriptionDto) {
        PrescriptionDto updatedPrescriptionDto = prescriptionService.updatePrescription(id, prescriptionDto);
        return ResponseEntity.ok(updatedPrescriptionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}

