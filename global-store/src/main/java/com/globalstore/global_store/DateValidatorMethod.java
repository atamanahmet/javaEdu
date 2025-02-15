package com.globalstore.global_store;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidatorMethod implements ConstraintValidator<DateCheck, Item> {
    private HttpServletRequest request;

    public DateValidatorMethod(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean isValid(Item item, ConstraintValidatorContext context) {

        if (item.getDate() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Date cannot be empty").addPropertyNode("date")
                    .addConstraintViolation();
            return false;
        }
        Date oldDate = (Date) request.getSession().getAttribute("oldDate");

        if (oldDate != null) {
            int difference = (int) Math.abs(TimeUnit.MILLISECONDS
                    .toDays(item.getDate().getTime() - oldDate.getTime()));

            if (difference > 5) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Date cannot be more than five days from old date")
                        .addPropertyNode("date")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
