package com.seeker.controller;

import com.seeker.dtos.ApiResponse;
import com.seeker.dtos.EducationQualificationRequestDto;
import com.seeker.dtos.EducationQualificationResponseDto;

import com.seeker.services.EducationQualificationService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seeker/education")
@RequiredArgsConstructor
public class EducationQualificationController {

    private final EducationQualificationService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid EducationQualificationRequestDto dto) {
        String message = service.create(dto);
        return ResponseEntity.ok(new ApiResponse(true, message));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getAllEducation(userId));
    }

    @PutMapping("/{educationId}")
    public ResponseEntity<?> update(
            @PathVariable Long educationId,
            @RequestBody @Valid EducationQualificationRequestDto dto
    ) {
        String message = service.update(educationId, dto);
        return ResponseEntity.ok(new ApiResponse(true, message));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
    	 String message = service.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, message));
    }
}
