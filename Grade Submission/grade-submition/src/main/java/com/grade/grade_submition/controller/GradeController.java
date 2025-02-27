package com.grade.grade_submition.controller;

import java.util.List;

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
    public ResponseEntity<Object> getGradeByStudentId(@PathVariable(value = "studentId") Long studentId,
            @PathVariable(value = "courseId") Long courseId) {

        Grade grade = gradeService.getGradeByStudentId(studentId);

        return (grade == null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @PostMapping("/grade/{studentId}")
    public ResponseEntity<Grade> createGrade(@Valid @RequestBody Grade grade, BindingResult result,
            @PathVariable(required = true) Long studentId) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else if (studentService.existsById(studentId)) {
            return new ResponseEntity<Grade>(gradeService.saveGrade(grade, studentId), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @DeleteMapping("grade/delete/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable(required = true) Long id) {
        gradeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("grade/delete/all")
    public ResponseEntity<HttpStatus> deleteAll() {
        gradeService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
