package com.grade.grade_submition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.Grade;
import com.grade.grade_submition.exceptions.ContentNotFoundException;
import com.grade.grade_submition.repository.CourseRepository;
import com.grade.grade_submition.repository.GradeRepository;
import com.grade.grade_submition.repository.StudentRepository;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

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

    public void deleteById(Long id) throws Exception {
        if (gradeRepository.existsById(id))
            gradeRepository.deleteById(id);
        else
            throw new ContentNotFoundException("Grade", id);
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

    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }

    public List<Grade> saveAll(List<Grade> grades) {
        return (List<Grade>) gradeRepository.saveAll(grades);
    }

    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {

        grade.setStudent(studentRepository.findById(studentId).get());
        grade.setCourse(courseRepository.findById(courseId).get());

        return gradeRepository.save(grade);
    }

    public List<Grade> getGradesByStudentId(Long studentId) {
        return (List<Grade>) gradeRepository.findAllByStudentId(studentId);
    }

    public Optional<Grade> getGradeByStudentIdAndCourseId(Long studentId, Long courseId) {
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    public void deleteAllByStudentIdAndCourseId(Long studentId, Long courseId) {
        gradeRepository.deleteAllByStudentIdAndCourseId(studentId, courseId);
    }
}