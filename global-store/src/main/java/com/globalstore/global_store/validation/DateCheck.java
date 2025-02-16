package com.globalstore.global_store.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateValidator.class)

public @interface DateCheck {
    String message() default "New date must be within 5 days";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
