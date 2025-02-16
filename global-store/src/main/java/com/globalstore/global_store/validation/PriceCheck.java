package com.globalstore.global_store.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
// import com.globalstore.global_store.validation.PriceValidator;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PriceValidator.class)
public @interface PriceCheck {
    String message() default "Price error. Error message not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
