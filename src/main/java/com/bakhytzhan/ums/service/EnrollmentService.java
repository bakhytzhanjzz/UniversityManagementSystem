package com.bakhytzhan.ums.service;

import com.bakhytzhan.ums.model.Enrollment;
import com.bakhytzhan.ums.model.Student;
import com.bakhytzhan.ums.model.Course;
import com.bakhytzhan.ums.repository.EnrollmentRepository;
import com.bakhytzhan.ums.repository.StudentRepository;
import com.bakhytzhan.ums.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // Enroll student in a course
    public Enrollment enrollStudent(UUID studentId, UUID courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment(student, course);
        return enrollmentRepository.save(enrollment);
    }

    // Get all enrollments
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // Get enrollment by ID
    public Enrollment getEnrollmentById(UUID id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    // Update enrollment
    public Enrollment updateEnrollment(UUID id, UUID courseId) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }

    // Delete enrollment
    public void deleteEnrollment(UUID id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new RuntimeException("Enrollment not found");
        }
        enrollmentRepository.deleteById(id);
    }
}
