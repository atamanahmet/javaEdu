package com.grade.grade_submition.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.Grade;
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

    public Grade saveGrade(Grade grade, Long studentId, Long courseId) {

        grade.setStudent(studentRepository.findById(studentId).get());
        grade.setCourse(courseRepository.findById(courseId).get());

        return gradeRepository.save(grade);
    }

    public List<Grade> getGradesByStudentId(Long studentId) {
        return (List<Grade>) gradeRepository.findAllByStudentId(studentId);
    }

    public Grade getGradeByStudentIdAndCourseId(Long studentId, Long courseId) {
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    // Without cascade, delete grades with student deletion

    // public void deleteGradesByStudentId(Long studentId) {

    // gradeRepository.deleteAllById(gradeRepository.findAllByStudentId(studentId)
    // .stream()
    // .map(grade -> grade.getId())
    // .collect(Collectors.toList()));

    // OLD
    // List<Long> gradeIdList = new ArrayList<>();

    // gradeRepository.findAllByStudentId(studentId).iterator()
    // .forEachRemaining(grade -> gradeIdList.add(grade.getId()));
    // gradeRepository.deleteAllById(gradeIdList);

    // }

    // Without interface custom method, getAllGradesFromStudent

    // public List<Grade> getGradesFromStudentId(Long studentId) {
    // List<Grade> gradeList = (List<Grade>) gradeRepository.findAll();
    // return gradeList.stream().filter(grade -> grade.getStudent().getId() ==
    // studentId).collect(Collectors.toList());

    // }

}