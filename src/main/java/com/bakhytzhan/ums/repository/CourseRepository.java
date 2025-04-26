package com.bakhytzhan.ums.repository;

import com.bakhytzhan.ums.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
