package com.grade.grade_submition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grade.grade_submition.domain.Grade;
import com.grade.grade_submition.domain.Student;
import com.grade.grade_submition.service.GradeService;
import com.grade.grade_submition.service.StudentService;

@RestController
// @CrossOrigin(origins = "http://localhost:3000")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<Object> getGrades(@RequestParam(required = false, value = "id") String id) {
        return new ResponseEntity<>(gradeService.getGradeList(), HttpStatus.OK);
    }

    @GetMapping("/grade/{id}")
    public ResponseEntity<Object> getGradeById(@RequestParam(required = false, value = "id") String id) {
        return new ResponseEntity<>(gradeService.getGrade(id), HttpStatus.OK);
    }

    @PostMapping("/grade")
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {

        HttpStatus status = gradeService.submitGrade(grade);
        return new ResponseEntity<Grade>(gradeService.getGrade(grade.getId()), status);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {

        return new ResponseEntity<Student>(studentService.save(student), HttpStatus.CREATED);
    }

    // Old

    // @GetMapping("/")
    // public String getForm(Model model, @RequestParam(value = "id", required =
    // false) String id) {

    // model.addAttribute("grade", gradeService.getGrade(id));

    // return "form";
    // }

    // @GetMapping("/grades")
    // public String getGrades(Model model) {

    // model.addAttribute("grades", gradeService.getGradeList());

    // return "grades";
    // }

    // @PostMapping("/handleSubmit")
    // public String handleSubmit(@Valid Grade grade, BindingResult bindingResult,
    // RedirectAttributes redirectAttributes) {

    // if (bindingResult.hasErrors())
    // return "form.html";

    // redirectAttributes.addFlashAttribute("status",
    // gradeService.submitGrade(grade));

    // return "redirect:/grades";
    // }

}
