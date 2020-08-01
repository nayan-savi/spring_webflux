package com.course.system.service;

import com.course.system.model.Course;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.UUID;

public interface ICourseService {

    boolean save(Course course) throws ParseException;

    Flux<Course> findAll();

    Mono<Void> delete(UUID uuid);

    Mono<Course> getById(UUID uuid);

    Mono<Course> updateById(Course course);


}
