package com.grade.grade_submition.service;

import java.util.List;

import com.grade.grade_submition.Constants;
import com.grade.grade_submition.domain.Grade;
import com.grade.grade_submition.repository.GradeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> getGradeList() {
        return gradeRepository.getGradeList();
    }

    public String updateGrade(int index, Grade grade) {
        gradeRepository.updateGrade(index, grade);
        return Constants.SUCCESS;

    }

    public String addGrade(Grade grade) {
        gradeRepository.addGrade(grade);
        return Constants.SUCCESS;
    }

    public int ifExistGetIndex(String id) {
        List<Grade> gradeList = gradeRepository.getGradeList();
        for (int i = 0; i < gradeList.size(); i++) {
            if (gradeList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    public Grade getGrade(String id) {
        int index = ifExistGetIndex(id);

        return (index == Constants.NOT_FOUND) ? new Grade() : gradeRepository.getGrade(index);
    }

    public HttpStatus submitGrade(Grade grade) {
        int index = ifExistGetIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            addGrade(grade);
            return HttpStatus.CREATED;
        } else {
            updateGrade(index, grade);
            return HttpStatus.ACCEPTED;
        }

    }

    public void retainId(String id, Grade grade) {
        grade.setId(id);
    }
}
