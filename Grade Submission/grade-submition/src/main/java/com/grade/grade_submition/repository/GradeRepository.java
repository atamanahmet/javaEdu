package com.grade.grade_submition.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grade.grade_submition.domain.Grade;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

}