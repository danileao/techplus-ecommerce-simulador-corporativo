package com.techplus.ecommerce.domain.model;

public class Product {

    private String name;
    private double price;
    private boolean isAvailable;

    public Product(String name, double price, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getName() {
        return name;
    }
}
