package com.techplus.ecommerce.domain.model.discount;

import com.techplus.ecommerce.domain.model.Order;

public class DiscounVoucherStrategy implements DiscountStrategy {
    @Override
    public double calculateDiscount(Order order) {
       if(order.getVoucherDiscount() != null && order.getVoucherDiscount().isValid()) {
           return order.getVoucherDiscount().getValue();
        }

       return 0;
    }
}
