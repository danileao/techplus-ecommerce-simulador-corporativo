package com.techplus.ecommerce.domain.model.discount;

import com.techplus.ecommerce.domain.model.Order;

public class DiscountVipStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(Order order) {
       if(order.getClient().isVip()) {
           double subtotal = order.getSubtotal();
           return subtotal * 0.05;
       }
       return  0;
    }
}
