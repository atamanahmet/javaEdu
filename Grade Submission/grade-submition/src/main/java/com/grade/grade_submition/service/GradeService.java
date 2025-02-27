package com.grade.grade_submition.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.Grade;
import com.grade.grade_submition.domain.Student;
import com.grade.grade_submition.repository.GradeRepository;
import com.grade.grade_submition.repository.StudentRepository;

@Service
public class GradeService {

    @Autowired
    GradeRepository gradeRepository;

    @Autowired
    StudentRepository studentRepository;

    public long count() {
        return gradeRepository.count();
    }

    public void delete(Grade grade) {
        gradeRepository.delete(grade);

    }

    public void deleteAll() {
        gradeRepository.deleteAll();

    }

    public void deleteAll(List<Grade> grades) {
        gradeRepository.deleteAll(grades);
    }

    public void deleteAllById(List<Long> ids) {
        gradeRepository.deleteAllById(ids);

    }

    public void deleteById(Long id) {
        gradeRepository.deleteById(id);

    }

    public boolean existsById(Long id) {
        return gradeRepository.existsById(id);
    }

    public List<Grade> findAll() {
        return (List<Grade>) gradeRepository.findAll();
    }

    public List<Grade> findAllById(List<Long> ids) {
        return (List<Grade>) gradeRepository.findAllById(ids);
    }

    public Grade findById(Long id) {
        return gradeRepository.findById(id).get();
    }

    public List<Grade> saveAll(List<Grade> grades) {
        return (List<Grade>) gradeRepository.saveAll(grades);
    }

    public Grade saveGrade(Grade grade, Long studentId) {
        if (studentRepository.existsById(studentId)) {
            grade.setStudent(studentRepository.findById(studentId).get());
        }

        return gradeRepository.save(grade);
    }

    public List<Grade> getGradesFromStudentId(Long studentId) {
        List<Grade> gradeList = (List<Grade>) gradeRepository.findAll();
        return gradeList.stream().filter(grade -> grade.getStudent().getId() == studentId).collect(Collectors.toList());

    }

}