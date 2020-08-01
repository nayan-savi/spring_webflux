package com.course.system.service;

import com.course.system.dao.CourseRepository;
import com.course.system.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public boolean save(Course course) {
        course.setId(UUID.randomUUID());
        course.setDays(getNumberOfDays(course));
        Disposable subscribe = courseRepository.save(course).subscribe();
        return subscribe.isDisposed();
    }

    private long getNumberOfDays(Course course) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sd.parse(course.getStartDate());
            Date endDate = sd.parse(course.getEndDate());
            long diff = endDate.getTime() - startDate.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Flux<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return courseRepository.deleteById(uuid);
    }

    @Override
    public Mono<Course> getById(UUID uuid) {
        return courseRepository.findById(uuid);
    }

    @Override
    public Mono<Course> updateById(Course course) {
        course.setDays(getNumberOfDays(course));
        return courseRepository.save(course);
    }
}
