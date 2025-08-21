package com.techplus.ecommerce.domain.model;

public class Customer {

    private String name;
    private boolean vip;

    public Customer(String name, boolean vip) {
        this.name = name;
        this.vip = vip;
    }

    public boolean isVip() {
        return vip;
    }

    public String getName() {
        return name;
    }
}
