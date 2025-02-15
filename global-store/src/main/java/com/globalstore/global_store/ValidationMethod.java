package com.globalstore.global_store;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidationMethod implements ConstraintValidator<PriceFieldCheck, Item> {
    @Override
    public boolean isValid(Item item, ConstraintValidatorContext context) {
        if (item.getPrice() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Price cannot be empty")
                    .addPropertyNode("price")
                    .addConstraintViolation();
            return false;
        }
        if (item.getDiscount() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Discount cannot be empty")
                    .addPropertyNode("price")
                    .addConstraintViolation();
            return false;
        }

        if (item.getPrice() < item.getDiscount()) {
            context.disableDefaultConstraintViolation();

            context.buildConstraintViolationWithTemplate("Price cannot be less than discount")
                    .addPropertyNode("price")
                    .addConstraintViolation();
            return false;

        }
        return true;

    }
}
