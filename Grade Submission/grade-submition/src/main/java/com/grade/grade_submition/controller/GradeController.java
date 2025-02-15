package com.grade.grade_submition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.grade.grade_submition.Grade;
import com.grade.grade_submition.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {

    private GradeService gradeService = new GradeService();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(value = "id", required = false) String id) {

        model.addAttribute("grade", gradeService.getGrade(id));

        return "form.html";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {

        model.addAttribute("grades", gradeService.getGradeList());

        return "grades.html";
    }

    @PostMapping("/handleSubmit")
    public String handleSubmit(@Valid Grade grade, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "form.html";
        }

        redirectAttributes.addFlashAttribute("status", gradeService.updateGrade(grade));

        return "redirect:/grades";
    }

}
