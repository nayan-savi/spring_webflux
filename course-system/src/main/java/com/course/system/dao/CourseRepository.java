package com.course.system.dao;

import com.course.system.model.Course;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends ReactiveMongoRepository<Course, UUID> {
}
