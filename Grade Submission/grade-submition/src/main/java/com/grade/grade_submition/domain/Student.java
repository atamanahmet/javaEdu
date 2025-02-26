package com.grade.grade_submition.domain;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name can not be empty")
    private String name;

    @Column(name = "birth_date", nullable = false)
    @NotNull(message = "Birth date can not be empty")
    private LocalDate birthDate;

    public Student() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getbirthDate() {
        return birthDate;
    }

    public void setbirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
