package com.grade.grade_submition;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public class Grade {
    @NotBlank(message = "Name field can not be blank")
    private String name;

    @NotBlank(message = "Subject field can not be blank")
    // @Min(4)
    private String subject;

    @NotBlank(message = "Score field can not be blank")
    // @Max(3)
    // @Pattern(regexp = "[ABCD][+-]|[F]")

    @Validation(message = "Wrong grade type. Must be letter")
    private String score;

    private String id;

    public Grade() {
        this.id = UUID.randomUUID().toString();
    }

    public Grade(String name, String subject, String score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
    }

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
