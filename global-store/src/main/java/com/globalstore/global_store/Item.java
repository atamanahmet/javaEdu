package com.globalstore.global_store;

import java.util.Date;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@PriceFieldCheck
@DateCheck
public class Item {
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    // @PriceValidation(message = "Price can not be lower than discount")

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message = "Date must be in the past")
    private Date date;

    @Min(value = 0, message = "Discount can not be negative")
    private Double discount;

    @Min(value = 0, message = "Price can not be negative")
    // @PriceValidation(message = "Price cannot be less than discount")
    private Double price;

    @NotBlank(message = "Please choose a category.")
    private String category;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setdate(Date date) {
        this.date = date;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
