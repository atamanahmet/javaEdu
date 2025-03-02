package com.grade.grade_submition.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.Course;
import com.grade.grade_submition.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public long courseCount() {
        return courseRepository.count();
    }

    public void deleteCourse(Course entity) {
        courseRepository.delete(entity);
    }

    public void deleteAllCourses() {
        courseRepository.deleteAll();
    }

    public void deleteAllCoursesByList(List<Course> courseList) {
        courseRepository.deleteAll(courseList);
    }

    public void deleteAllById(List<Long> idList) {
        courseRepository.deleteAllById(idList);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public boolean isExistsById(Long id) {
        return courseRepository.existsById(id);
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        courseRepository.findAll().iterator().forEachRemaining(course -> courseList.add(course));
        return courseList;
    }

    public List<Course> getAllByIdList(List<Long> idList) {

        Iterable<Course> result = courseRepository.findAllById(idList);
        if (result != null) {
            List<Course> courseList = new ArrayList<>();
            courseRepository.findAllById(idList).iterator().forEachRemaining(course -> courseList.add(course));
            return courseList;
        }
        return null;
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> saveAllCourses(List<Course> courseList) {
        List<Course> savedCourseList = new ArrayList<>();
        courseRepository.saveAll(courseList).iterator().forEachRemaining(course -> savedCourseList.add(course));
        return savedCourseList;
    }

}
