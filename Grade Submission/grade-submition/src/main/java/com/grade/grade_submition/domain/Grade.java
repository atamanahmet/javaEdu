package com.grade.grade_submition.domain;

// import java.util.UUID;

import com.grade.grade_submition.Validation;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Grade {
    @Column(name = "name")
    @NotBlank(message = "Name field can not be blank")
    private String name;

    @Column(name = "subject")
    @NotBlank(message = "Subject field can not be blank")
    private String subject;

    @Column(name = "score")
    @NotBlank(message = "Score field can not be blank")
    @Validation(message = "Wrong grade type. Must be letter")
    private String score;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    public Grade() {
        // this.id = UUID.randomUUID().toString();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
