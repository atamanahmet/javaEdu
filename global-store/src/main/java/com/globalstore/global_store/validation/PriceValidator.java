package com.globalstore.global_store.validation;

import com.globalstore.global_store.Item;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<PriceCheck, Item> {

    public boolean isValid(Item item, ConstraintValidatorContext context) {
        if (item.getPrice() < item.getDiscount()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Price cannot be lower than discount").addPropertyNode("price")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
