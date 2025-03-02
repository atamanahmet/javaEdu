package com.grade.grade_submition.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grade.grade_submition.domain.Grade;
import com.grade.grade_submition.service.CourseService;
import com.grade.grade_submition.service.GradeService;
import com.grade.grade_submition.service.StudentService;

import jakarta.validation.Valid;

@RestController
// @CrossOrigin(origins = "http://localhost:3000")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/grade")
    public ResponseEntity<Object> getGrades() {
        return new ResponseEntity<>(gradeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/grade/student/{studentId}")
    public ResponseEntity<Object> getGradesByStudentId(@PathVariable(value = "studentId") Long studentId) {
        List<Grade> list = gradeService.getGradesByStudentId(studentId);
        System.out.println(list);

        return (list.isEmpty()) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/grade/student/{studentId}/course/{courseId}")
    public ResponseEntity<Optional<Grade>> getGradeByBothIds(@PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "courseId") Long courseId) {

        Optional<Grade> grade = gradeService.getGradeByStudentIdAndCourseId(studentId, courseId);

        return (grade.isPresent()) ? new ResponseEntity<>(grade, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/grade/course/{courseId}/student/{studentId}")
    public ResponseEntity<?> createGrade(@Valid @RequestBody Grade grade, BindingResult result,
            @PathVariable(required = true) Long studentId,
            @PathVariable(required = true, value = "courseId") Long courseId) {

        if (result.hasErrors()) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);

        } else if (studentService.existsById(studentId) && courseService.isExistsById(courseId)) {

            try {

                courseService.saveStudentList(courseId, studentService.findById(studentId).get());
                studentService.saveCourseList(studentId, courseService.getCourseById(courseId).get());

                return new ResponseEntity<Grade>(gradeService.saveGrade(grade, studentId, courseId),
                        HttpStatus.CREATED);
            } catch (Exception e) {

                return new ResponseEntity<String>(
                        "Duplicate grade. Each student can have only one grade from each course.",
                        HttpStatus.NOT_ACCEPTABLE);
            }

        } else {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("/grade/delete/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteWithBothIds(
            @PathVariable(required = true, value = "studentId") Long studentId,
            @PathVariable(required = true, value = "courseId") Long courseId) {
        if (studentService.existsById(studentId) && courseService.isExistsById(courseId)) {
            gradeService.deleteAllByStudentIdAndCourseId(studentId, courseId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("grade/delete/all")
    public ResponseEntity<HttpStatus> deleteAll() {
        gradeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
