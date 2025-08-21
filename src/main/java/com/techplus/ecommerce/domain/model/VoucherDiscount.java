package com.techplus.ecommerce.domain.model;

import java.time.LocalDate;

public class VoucherDiscount {
    private String code;
    private double value;
    private LocalDate expirationDate;

    public VoucherDiscount(String code, double value, LocalDate expirationDate) {
        this.code = code;
        this.value = value;
        this.expirationDate = expirationDate;
    }

    public boolean isValid() {
        return LocalDate.now().isBefore(expirationDate);
    }

    public double getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }
}
