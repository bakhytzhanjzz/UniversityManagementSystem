package com.bakhytzhan.ums.controller;

import com.bakhytzhan.ums.dto.CreateCourseRequest;
import com.bakhytzhan.ums.dto.UpdateCourseRequest;
import com.bakhytzhan.ums.mapper.CourseMapper;
import com.bakhytzhan.ums.model.Course;
import com.bakhytzhan.ums.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CreateCourseRequest request) {
        // Map DTO to Entity
        Course course = CourseMapper.toCourse(request);

        // Pass the entity to the service layer
        Course createdCourse = courseService.createCourse(course);

        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable UUID id, @RequestBody UpdateCourseRequest request) {
        try {
            Course course = courseService.getCourseById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            CourseMapper.updateCourse(course, request);
            courseService.updateCourse(id, course);
            return ResponseEntity.ok(course);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
