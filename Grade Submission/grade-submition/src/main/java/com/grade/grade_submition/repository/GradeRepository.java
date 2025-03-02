package com.grade.grade_submition.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.grade.grade_submition.domain.Grade;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {
    List<Grade> findAllByStudentId(Long studentId);

    Optional<Grade> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Transactional
    void deleteAllByStudentIdAndCourseId(Long studentId, Long courseId);

}