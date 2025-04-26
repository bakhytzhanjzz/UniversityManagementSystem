package com.bakhytzhan.ums.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EnrollmentRequest {
    private UUID studentId;
    private UUID courseId;

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public void setCourseId(UUID courseId) {
        this.courseId = courseId;
    }
}
