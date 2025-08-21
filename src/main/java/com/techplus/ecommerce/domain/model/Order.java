package com.techplus.ecommerce.domain.model;

import com.techplus.ecommerce.domain.model.discount.DiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;
    private List<OrderItem> itens = new ArrayList<>();
    private VoucherDiscount voucherDiscount;
    List<DiscountStrategy> strategies = new ArrayList<>();

    public Order(Customer customer) {
        this.customer = customer;
    }

    public void addItem(OrderItem item) {
        if (!item.getProduct().isAvailable()) {
            throw new IllegalArgumentException("Produto fora de estoque: " + item.getProduct().getName());
        }
        itens.add(item);
    }

    public void addVoucher(VoucherDiscount voucherDiscount) {
        if (voucherDiscount != null && voucherDiscount.isValid()) {
            this.voucherDiscount = voucherDiscount;
        }
    }

    public void addStrategy(DiscountStrategy strategies) {
        this.strategies.add(strategies);
    }

    public double getSubtotal() {
        return itens.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }

    public double calculateTotal() {
        double subtotal = getSubtotal();

        double discountTotal = strategies.stream().mapToDouble(e -> e.calculateDiscount(this)).sum();

        return subtotal - discountTotal;
    }


    public VoucherDiscount getVoucherDiscount() {
        return voucherDiscount;
    }

    public Customer getClient() {
        return customer;
    }
}
