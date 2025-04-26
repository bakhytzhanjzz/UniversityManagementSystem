package com.bakhytzhan.ums.mapper;

import com.bakhytzhan.ums.dto.CreateCourseRequest;
import com.bakhytzhan.ums.dto.UpdateCourseRequest;
import com.bakhytzhan.ums.model.Course;

public class CourseMapper {

    public static Course toCourse(CreateCourseRequest request) {
        return Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .credits(request.getCredits())
                .build();
    }

    public static void updateCourse(Course course, UpdateCourseRequest request) {
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setCredits(request.getCredits());
    }
}
