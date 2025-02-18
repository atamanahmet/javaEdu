package com.grade.grade_submition.repository;

import java.util.ArrayList;
import java.util.List;

import com.grade.grade_submition.Grade;
import org.springframework.stereotype.Repository;

@Repository
public class GradeRepository {

    private List<Grade> gradeList;

    public GradeRepository() {
        this.gradeList = new ArrayList<>();
    }

    public List<Grade> getGradeList() {
        return this.gradeList;
    }

    public void updateGrade(int index, Grade grade) {
        gradeList.set(index, grade);
    }

    public Grade getGrade(int index) {
        return gradeList.get(index);
    }

    public void addGrade(Grade grade) {
        this.gradeList.add(grade);
    }

}
