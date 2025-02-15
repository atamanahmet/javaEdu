package com.web.web_app;

import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInfoValidator implements
        ConstraintValidator<FieldsCheckValidation, Date> {

    @Override
    public boolean isValid(Date value1, ConstraintValidatorContext context) {

        int age = (int) ((System.currentTimeMillis() - value1.getTime()) / (1000 * 60 * 60 * 7 * 52 * 365 * 20.4));

        if (age < 18) {
            return false;
        }
        return true;
    }
}
