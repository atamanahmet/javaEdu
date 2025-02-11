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
    public boolean isFirstStart = true;

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        if (studentGrades.isEmpty()) {
            populateGrades();

        }
        // studentGrades = new ArrayList<>();
        isFirstStart = false;

        int index = getIndex(id);
        Grade grade = (index == Constants.NOT_FOUND) ? new Grade() : studentGrades.get(index);
        model.addAttribute("grade", grade);
        return "form";
    }

    @GetMapping("/grades")
    public String getGrades(Model model) {
        studentGrades = new ArrayList<>();
        populateGrades();
        model.addAttribute("grades", studentGrades);
        return "grades";
    }

    @PostMapping("/handleSubmit")
    public String submitGrades(Grade grade) {
        boolean isExist = false;
        for (Grade existingGrade : studentGrades) {
            if (existingGrade.getId().equals(grade.getId())) {
                existingGrade.setName(grade.getName());
                existingGrade.setSubject(grade.getSubject());
                existingGrade.setScore(grade.getScore());
                isExist = true;
            }

        }
        if (!isExist) {
            studentGrades.add(grade);
        }

        updateGradesWithId();

        return "form";
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
        String dataFileName;

        try {
            if (isFirstStart) {
                dataFileName = "data.csv";

            } else {
                dataFileName = "data.txt";
            }
            BufferedReader buffRead = new BufferedReader(new FileReader(dataFileName));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = buffRead.readLine()) != null) {
                lines.add(line);
            }
            for (String string : lines) {
                String[] datas = string.split(",");
                String name = datas[0];
                String subject = datas[1];
                String score = datas[2];
                String id = datas[3];

                studentGrades.add(new Grade(name, subject, score, id));

            }

            buffRead.close();
            updateGradesWithId();
        } catch (Exception e) {
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