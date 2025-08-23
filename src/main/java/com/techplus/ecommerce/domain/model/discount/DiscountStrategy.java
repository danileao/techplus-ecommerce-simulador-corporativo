package com.techplus.ecommerce.domain.model.discount;

import com.techplus.ecommerce.domain.model.Order;

public interface DiscountStrategy {

    double calculateDiscount(Order order);
}
