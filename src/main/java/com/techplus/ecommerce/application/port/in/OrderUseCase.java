package com.techplus.ecommerce.application.port.in;

import com.techplus.ecommerce.domain.model.Order;
import com.techplus.ecommerce.domain.model.OrderItem;
import com.techplus.ecommerce.domain.model.VoucherDiscount;

public interface OrderUseCase {

    void addItem(Order order, String productName, int quantity);
    void addVoucher(Order order, VoucherDiscount voucherDiscount);
    double calculateTotal(Order order);
}
