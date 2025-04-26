package com.bakhytzhan.ums.controller;

import com.bakhytzhan.ums.model.Enrollment;
import com.bakhytzhan.ums.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@RequestParam UUID studentId, @RequestParam UUID courseId) {
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        return new ResponseEntity<>(enrollment, HttpStatus.CREATED);
    }
}
