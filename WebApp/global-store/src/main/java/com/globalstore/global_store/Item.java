package com.globalstore.global_store;

import java.util.UUID;

public class Item {
    private String name;
    private String discount;
    private String category;
    private String price;
    private String date;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
