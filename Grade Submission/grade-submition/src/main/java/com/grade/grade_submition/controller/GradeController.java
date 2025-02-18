package com.grade.grade_submition.controller;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

import com.grade.grade_submition.Grade;
import com.grade.grade_submition.service.GradeService;

import jakarta.validation.Valid;

@Controller
// @CrossOrigin(origins = "http://localhost:3000")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(value = "id", required = false) String id) {

        model.addAttribute("grade", gradeService.getGrade(id));

        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {

        model.addAttribute("grades", gradeService.getGradeList());

        return "grades";
    }

    // @GetMapping("/grades")
    // public ResponseEntity<List<Grade>> getGrades() {

    // List<Grade> asd = new ArrayList<>();

    // asd.add(new Grade("a", "a", "a"));
    // asd.add(new Grade("b", "b", "b"));
    // asd.add(new Grade("c", "c", "c"));

    // return new ResponseEntity<>(asd, HttpStatus.OK);
    // }

    @PostMapping("/handleSubmit")
    public String handleSubmit(@Valid Grade grade, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors())
            return "form.html";

        redirectAttributes.addFlashAttribute("status",
                gradeService.submitGrade(grade));

        return "redirect:/grades";
    }

}
