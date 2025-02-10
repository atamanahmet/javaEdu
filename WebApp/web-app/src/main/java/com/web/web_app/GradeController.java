package com.web.web_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {

        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        Grade grade = new Grade("Harry", "Physics", "C-");
        model.addAttribute(grade);
        return "grades";
    }

    @GetMapping("/form")
    public String getForm(Model model) {
        return "form";
    }

}