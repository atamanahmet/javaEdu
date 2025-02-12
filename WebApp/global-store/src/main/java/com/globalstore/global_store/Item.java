package com.globalstore.global_store;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public class Item {
    private String name;
    private int discount;
    private String category;
    private int price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String id;

    public Item() {
        this.id = UUID.randomUUID().toString();
    }

    // public Item(String name, String discount, String category, String price,
    // String date) {
    // this.name = name;
    // this.discount = discount;
    // this.category = category;
    // this.price = price;
    // this.date = date;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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
