package com.globalstore.global_store;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class Validator implements ConstraintValidator<PriceValidation, Double> {
    @Override
    public boolean isValid(Double price, ConstraintValidatorContext context) {
        if (price < 5) {
            return false;
        }
        return true;
    }

}
