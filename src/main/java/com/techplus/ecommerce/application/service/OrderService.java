package com.techplus.ecommerce.application.service;

import com.techplus.ecommerce.application.port.in.OrderUseCase;
import com.techplus.ecommerce.application.port.out.ProductRepository;
import com.techplus.ecommerce.domain.model.Order;
import com.techplus.ecommerce.domain.model.OrderItem;
import com.techplus.ecommerce.domain.model.Product;
import com.techplus.ecommerce.domain.model.VoucherDiscount;

public class OrderService implements OrderUseCase {


    private ProductRepository productRepository;

    public OrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public void addItem(Order order, String productName, int quantity) {
        Product product
                 = productRepository.findByName(productName)
                .orElseThrow(() -> new IllegalArgumentException("Product Not Found"));
        order.addItem(new OrderItem(product, quantity));
    }

    @Override
    public void addVoucher(Order order, VoucherDiscount voucherDiscount) {
        order.addVoucher(voucherDiscount);
    }

    @Override
    public double calculateTotal(Order order) {
        return order.calculateTotal();
    }
}
