package com.bakhytzhan.ums.mapper;

import com.bakhytzhan.ums.dto.CreateStudentRequest;
import com.bakhytzhan.ums.dto.UpdateStudentRequest;
import com.bakhytzhan.ums.model.Student;

public class StudentMapper {

    public static Student toStudent(CreateStudentRequest request) {
        return Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();
    }

    public static void updateStudent(Student student, UpdateStudentRequest request) {
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
    }
}
