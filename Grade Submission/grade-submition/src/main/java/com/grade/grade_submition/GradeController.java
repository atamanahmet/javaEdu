package com.grade.grade_submition;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GradeController {
    List<Grade> gradeList = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(value = "id", required = false) String id) {

        int index = ifExistGetIndex(id);

        Grade grade = (index == Constants.NOT_FOUND) ? new Grade() : gradeList.get(index);

        model.addAttribute("grade", grade);

        return "form.html";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {

        model.addAttribute("grades", gradeList);

        return "grades.html";
    }

    @PostMapping("/handleSubmit")
    public String handleSubmit(@Valid Grade grade, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            return "form.html";
        }
        String status;
        int index = ifExistGetIndex(grade.getId());
        grade.setScore(grade.getScore().toUpperCase());

        if (index == Constants.NOT_FOUND) {

            gradeList.add(grade);
            status = Constants.success;
        } else {
            gradeList.set(index, grade);
            status = Constants.success;

        }

        redirectAttributes.addFlashAttribute("status", status);

        return "redirect:/grades";
    }

    public int ifExistGetIndex(String id) {
        for (int i = 0; i < gradeList.size(); i++) {
            if (gradeList.get(i).getId().equals(id)) {
                return i;
            }

        }
        return Constants.NOT_FOUND;
    }

}
