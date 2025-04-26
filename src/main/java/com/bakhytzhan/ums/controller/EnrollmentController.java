package com.bakhytzhan.ums.controller;

import com.bakhytzhan.ums.model.Enrollment;
import com.bakhytzhan.ums.service.EnrollmentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Data
    static class EnrollmentRequest {
        private UUID studentId;
        private UUID courseId;
    }

    // Enroll student to a course (POST)
    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@RequestBody EnrollmentRequest request) {
        Enrollment enrollment = enrollmentService.enrollStudent(request.getStudentId(), request.getCourseId());
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }

    // Get all enrollments (GET)
    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
        return new ResponseEntity<>(enrollments, HttpStatus.OK);
    }

    // Get specific enrollment by ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable UUID id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);
        return new ResponseEntity<>(enrollment, HttpStatus.OK);
    }

    @Data
    static class UpdateEnrollmentRequest {
        private UUID courseId;
    }

    // Update enrollment (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable UUID id, @RequestBody UpdateEnrollmentRequest request) {
        Enrollment updatedEnrollment = enrollmentService.updateEnrollment(id, request.getCourseId());
        return new ResponseEntity<>(updatedEnrollment, HttpStatus.OK);
    }

    // Delete enrollment (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable UUID id) {
        enrollmentService.deleteEnrollment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
