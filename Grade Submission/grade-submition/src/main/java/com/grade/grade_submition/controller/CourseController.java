package com.grade.grade_submition.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.grade.grade_submition.domain.Course;
import com.grade.grade_submition.service.CourseService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CourseController {
    // Singleton
    @Autowired
    private CourseService courseService;

    @GetMapping("/course")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Course> getCourseById(
            @PathVariable(required = true, value = "courseId") Long courseId) {
        Optional<Course> course = courseService.getCourseById(courseId);

        return (course.isPresent()) ? new ResponseEntity<>(course.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/course")
    public ResponseEntity<?> saveCourse(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Course already exist. Enter a different course.",
                    HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/course/delete/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable(required = true, value = "courseId") Long courseId) {
        if (courseService.isExistsById(courseId)) {
            courseService.deleteById(courseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
