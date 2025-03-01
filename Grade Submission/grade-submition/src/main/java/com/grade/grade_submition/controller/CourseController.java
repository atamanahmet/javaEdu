package com.grade.grade_submition.controller;

import java.util.List;

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

    @PostMapping("/course")
    public ResponseEntity<Course> saveCourse(@Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(courseService.saveCourse(course), HttpStatus.CREATED);
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
