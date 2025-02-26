package com.grade.grade_submition.domain;

import com.grade.grade_submition.validation.Validation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "grade")
public class Grade {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score", nullable = false)
    @NotBlank(message = "Score field can not be blank")
    @Validation(message = "Wrong grade type. Must be letter")
    private String score;

    public Grade() {
    }

    // Test
    // public Grade(String name, String subject, String score) {
    // this.name = name;
    // this.subject = subject;
    // this.score = score;
    // if (this.id == null) {
    // this.id = UUID.randomUUID().toString();

    // }
    // }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
