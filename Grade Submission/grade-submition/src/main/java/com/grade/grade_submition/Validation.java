package com.grade.grade_submition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ScoreTypeValidator.class)
public @interface Validation {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String message() default "Enter a valid letter grade";
}
