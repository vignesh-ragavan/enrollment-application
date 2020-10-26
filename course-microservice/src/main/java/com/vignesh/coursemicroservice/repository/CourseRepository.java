package com.vignesh.coursemicroservice.repository;

import com.vignesh.coursemicroservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
