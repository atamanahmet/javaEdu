package com.globalstore.global_store;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = Validator.class)
public @interface PriceValidation {

    String message() default "Please enter correct info";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
