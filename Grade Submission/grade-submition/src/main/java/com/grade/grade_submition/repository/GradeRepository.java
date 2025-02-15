package com.grade.grade_submition.repository;

import java.util.ArrayList;
import java.util.List;

import com.grade.grade_submition.Constants;
import com.grade.grade_submition.Grade;

public class GradeRepository {
    private List<Grade> gradeList = new ArrayList<>();

    public List<Grade> getGradeList() {
        return this.gradeList;
    }

    public int ifExistGetIndex(String id) {
        for (int i = 0; i < gradeList.size(); i++) {
            if (gradeList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public String updateGrade(Grade grade) {
        int index = ifExistGetIndex(grade.getId());

        if (index == Constants.NOT_FOUND) {
            gradeList.add(grade);
            System.out.println("New grade created");
        } else {
            gradeList.set(index, grade);
        }
        return Constants.success;
    }

    public Grade getGrade(String id) {
        int index = ifExistGetIndex(id);

        return (index == Constants.NOT_FOUND) ? new Grade() : gradeList.get(index);
    }

}
