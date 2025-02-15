package com.web.web_app;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserInfoValidator.class)
public @interface FieldsCheckValidation {

    String message() default "Please enter correct info";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
