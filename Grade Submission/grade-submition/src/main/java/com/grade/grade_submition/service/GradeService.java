package com.grade.grade_submition.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.Grade;
import com.grade.grade_submition.repository.GradeRepository;

@Service
public class GradeService implements GradeRepository {

    @Autowired
    GradeRepository gradeRepository;

    @Override
    public long count() {
        return gradeRepository.count();
    }

    @Override
    public void delete(Grade grade) {
        gradeRepository.delete(grade);

    }

    @Override
    public void deleteAll() {
        gradeRepository.deleteAll();

    }

    @Override
    public void deleteAll(Iterable<? extends Grade> entities) {
        gradeRepository.deleteAll(entities);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        gradeRepository.deleteAllById(ids);

    }

    @Override
    public void deleteById(Long id) {
        gradeRepository.deleteById(id);

    }

    @Override
    public boolean existsById(Long id) {
        return gradeRepository.existsById(id);
    }

    @Override
    public Iterable<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Iterable<Grade> findAllById(Iterable<Long> ids) {
        return gradeRepository.findAllById(ids);
    }

    @Override
    public Optional<Grade> findById(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Grade save(Grade grade) {
        return (Grade) gradeRepository.save(grade);
    }

    @Override
    public <S extends Grade> Iterable<S> saveAll(Iterable<S> grades) {
        return gradeRepository.saveAll(grades);
    }

}