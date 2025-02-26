package com.grade.grade_submition.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.Student;
import com.grade.grade_submition.repository.StudentRepository;

@Service
public class StudentService implements StudentRepository {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public long count() {
        return studentRepository.count();
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);

    }

    @Override
    public void deleteAll() {
        studentRepository.deleteAll();

    }

    @Override
    public void deleteAll(Iterable<? extends Student> students) {
        studentRepository.deleteAll(students);

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        studentRepository.deleteAllById(ids);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);

    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);

    }

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Iterable<Student> findAllById(Iterable<Long> ids) {
        return studentRepository.findAllById(ids);
    }

    @Override
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public <S extends Student> Iterable<S> saveAll(Iterable<S> students) {
        return studentRepository.saveAll(students);
    }

}
