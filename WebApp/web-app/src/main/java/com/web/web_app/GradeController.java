package com.web.web_app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {

    List<Grade> studentGrades = new ArrayList<>();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        studentGrades = new ArrayList<>();
        populateGrades();
        int index = getIndex(id);
        Grade grade = (index == Constants.NOT_FOUND) ? new Grade() : studentGrades.get(index);
        model.addAttribute(grade);
        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        studentGrades = new ArrayList<>();
        populateGrades();
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    public int getIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;

    }

    public void populateGrades() {
        try {
            BufferedReader buffRead = new BufferedReader(new FileReader("data.csv"));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = buffRead.readLine()) != null) {
                lines.add(line);
            }
            List<Grade> bufferGrades = new ArrayList<>(studentGrades);
            for (String string : lines) {
                String name = string.split(",")[0];
                String subject = string.split(",")[1];
                String score = string.split(",")[2];
                // System.out.println(name + subject + score);
                // if (!bufferGrades.isEmpty()) {
                // for (Grade grade : bufferGrades) {

                // }
                // } else {
                studentGrades.add(new Grade(name, subject, score));

                // }

            }
            // for (Grade grade : studentGrades) {
            // System.out.println(grade.getName() + grade.getSubject() + grade.getScore());

            // }
            buffRead.close();
            updateGradesWithId();
        } catch (Exception e) {
            // System.out.println("error");
            System.out.println(e.getMessage());
        }
    }

    public void updateGradesWithId() {
        try {
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("data.txt", false));
            for (Grade grade : studentGrades) {

                buffWrite
                        .write(grade.getName() + "," + grade.getSubject() + "," + grade.getScore() + "," + grade.getId()
                                + "\n");

            }
            buffWrite.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}