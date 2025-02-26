package com.grade.grade_submition.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.grade.grade_submition.domain.Grade;
import com.grade.grade_submition.service.GradeService;

import jakarta.validation.Valid;

@RestController
// @CrossOrigin(origins = "http://localhost:3000")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/grade")
    public ResponseEntity<Object> getGrades(@RequestParam(required = false, value = "id") Long id) {
        return new ResponseEntity<>(gradeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/grade/{id}")
    public ResponseEntity<Object> getGradeById(@RequestParam(required = false, value = "id") Long id) {
        return new ResponseEntity<>(gradeService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/grade")
    public ResponseEntity<Grade> createGrade(@Valid @RequestBody Grade grade, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<Grade>(HttpStatus.NOT_ACCEPTABLE);

        }
        return new ResponseEntity<Grade>(gradeService.save(grade), HttpStatus.CREATED);
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
