package com.bakhytzhan.ums.service;

import com.bakhytzhan.ums.model.Course;
import com.bakhytzhan.ums.model.Enrollment;
import com.bakhytzhan.ums.model.Student;
import com.bakhytzhan.ums.repository.CourseRepository;
import com.bakhytzhan.ums.repository.EnrollmentRepository;
import com.bakhytzhan.ums.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public Enrollment enrollStudent(UUID studentId, UUID courseId) {
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new RuntimeException("Student is already enrolled in this course");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrollmentDate(LocalDate.now())
                .build();

        return enrollmentRepository.save(enrollment);
    }
}
