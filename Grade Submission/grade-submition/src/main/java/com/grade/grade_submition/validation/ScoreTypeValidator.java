package com.grade.grade_submition.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ScoreTypeValidator implements ConstraintValidator<Validation, String> {
    String[] validGrades = { "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F" };

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String grade : validGrades) {
            if (value.toLowerCase().equals(grade.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

}
