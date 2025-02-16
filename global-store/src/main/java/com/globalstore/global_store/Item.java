package com.globalstore.global_store;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.globalstore.global_store.validation.DateCheck;
import com.globalstore.global_store.validation.PriceCheck;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@PriceCheck
@DateCheck
public class Item {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Please select a category")
    private String category;

    @Min(0)
    private int price;

    @Min(0)
    private int discount;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String id;

    public Item() {
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
