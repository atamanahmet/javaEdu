package com.grade.grade_submition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.grade.grade_submition.domain.Student;
import com.grade.grade_submition.service.GradeService;
import com.grade.grade_submition.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;

    @GetMapping("/student")
    public ResponseEntity<Object> getStudents() {
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Object> getStudentById(@PathVariable(required = false, value = "id") Long id) {
        if (studentService.existsById(id)) {
            return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/student/delete/{studentId}")
    public ResponseEntity<Object> deleteStudentById(
            @PathVariable(required = true, value = "studentId") Long studentId) {

        gradeService.deleteGradesByStudentId(studentId);

        studentService.deleteById(studentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student, BindingResult result) {

        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<Student>(studentService.save(student), HttpStatus.CREATED);
    }
}
