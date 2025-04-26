package com.bakhytzhan.ums.controller;

import com.bakhytzhan.ums.dto.CreateStudentRequest;
import com.bakhytzhan.ums.dto.UpdateStudentRequest;
import com.bakhytzhan.ums.mapper.StudentMapper;
import com.bakhytzhan.ums.model.Student;
import com.bakhytzhan.ums.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody CreateStudentRequest request) {
        // Map DTO to Entity
        Student student = StudentMapper.toStudent(request);

        // Pass the entity to the service layer
        Student created = studentService.createStudent(student);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable UUID id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable UUID id, @RequestBody UpdateStudentRequest request) {
        try {
            // Retrieve the existing student first
            Student student = studentService.getStudentById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            // Map the update DTO to entity and apply changes
            StudentMapper.updateStudent(student, request);

            // Save and return updated student
            studentService.updateStudent(id, student);

            return ResponseEntity.ok(student);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
