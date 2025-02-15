package com.grade.grade_submition.service;

import java.util.List;
import com.grade.grade_submition.Grade;
import com.grade.grade_submition.repository.GradeRepository;

public class GradeService {
    private GradeRepository gradeRepository = new GradeRepository();

    public Grade getGrade(String id) {
        return gradeRepository.getGrade(id);
    }

    public List<Grade> getGradeList() {
        return gradeRepository.getGradeList();
    }

    public String updateGrade(Grade grade) {
        return gradeRepository.updateGrade(grade);

    }
}
