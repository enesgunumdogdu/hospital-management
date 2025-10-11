package com.enesgunumdogdu.hospital_management.controller;

import com.enesgunumdogdu.hospital_management.domain.dto.ExaminationDto;
import com.enesgunumdogdu.hospital_management.service.ExaminationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examinations")
public class ExaminationController {
    
    private final ExaminationService examinationService;

    public ExaminationController(ExaminationService examinationService) {
        this.examinationService = examinationService;
    }

    @GetMapping
    public ResponseEntity<List<ExaminationDto>> getAllExaminations() {
        List<ExaminationDto> examinations = examinationService.getAllExaminations();
        return ResponseEntity.ok(examinations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminationDto> getExaminationById(@PathVariable Long id) {
        ExaminationDto examinationDto = examinationService.getExaminationById(id);
        return ResponseEntity.ok(examinationDto);
    }

    @PostMapping
    public ResponseEntity<ExaminationDto> createExamination(@RequestBody ExaminationDto examinationDto) {
        ExaminationDto createdExaminationDto = examinationService.createExamination(examinationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdExaminationDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExaminationDto> updateExamination(@PathVariable Long id, @RequestBody ExaminationDto examinationDto) {
        ExaminationDto updatedExaminationDto = examinationService.updateExamination(id, examinationDto);
        return ResponseEntity.ok(updatedExaminationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExamination(@PathVariable Long id) {
        examinationService.deleteExamination(id);
        return ResponseEntity.noContent().build();
    }
}

