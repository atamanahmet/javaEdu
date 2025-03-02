package com.grade.grade_submition.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grade.grade_submition.domain.Student;
import com.grade.grade_submition.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public long count() {
        return studentRepository.count();
    }

    public void delete(Student student) {
        studentRepository.delete(student);

    }

    public void deleteAll() {
        studentRepository.deleteAll();

    }

    public void deleteAll(List<Student> students) {
        studentRepository.deleteAll(students);

    }

    public void deleteAllById(List<Long> ids) {
        studentRepository.deleteAllById(ids);
    }

    public void deleteById(Long id) {
        studentRepository.deleteById(id);

    }

    public boolean existsById(Long id) {
        return studentRepository.existsById(id);

    }

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public List<Student> findAllById(List<Long> ids) {
        return (List<Student>) studentRepository.findAllById(ids);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> saveAll(List<Student> students) {
        return (List<Student>) studentRepository.saveAll(students);
    }

}
