package com.course.system.controller;

import com.course.system.model.Course;
import com.course.system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@RequestBody Course course) {
        boolean isCreated = courseService.save(course);
        return ResponseEntity.ok(isCreated);
    }

    @ResponseBody
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Course> getById(@PathVariable("id") UUID uuid) {
        return courseService.getById(uuid);
    }


    @ResponseBody
    @RequestMapping(value = "/course", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Course> updateById(@RequestBody Course course) {
        return courseService.updateById(course);
    }


    @ResponseBody
    @RequestMapping(value = "/courses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Course> findAll() {
        return courseService.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") UUID uuid) {
        courseService.delete(uuid).subscribe();
    }

}
