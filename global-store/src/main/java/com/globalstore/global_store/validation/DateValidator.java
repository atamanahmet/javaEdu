package com.globalstore.global_store.validation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.globalstore.global_store.Constants;
import com.globalstore.global_store.Item;
import com.globalstore.global_store.service.StoreService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateCheck, Item> {

    @Autowired
    private StoreService storeService;

    public boolean isValid(Item item, ConstraintValidatorContext context) {

        int index = storeService.getItemIndex(item.getId());

        if (index == Constants.NOT_FOUND) {

            return true;

        } else {
            Date oldDate = storeService.getOldDate(item.getId());

            if (oldDate == null) {
                System.out.println("oldDate: null");
            }
            LocalDate localdate = LocalDate.ofInstant(oldDate.toInstant(), ZoneId.systemDefault());

            if (Math.abs(TimeUnit.MILLISECONDS.toDays(item.getDate().getTime() - oldDate.getTime())) > 5) {

                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "Date must be within 5 days from "
                                + localdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .addPropertyNode("date")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
