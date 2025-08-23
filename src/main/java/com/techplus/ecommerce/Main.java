package com.techplus.ecommerce;

import com.techplus.ecommerce.domain.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer cliente = new Customer("Dani", true);
        Product produto1 = new Product("Notebook", 3500.00, true);
        Product produto2 = new Product("Mouse", 150.00, true);

        Order order = new Order(cliente);
        order.addItem(new OrderItem(produto1, 1));
        order.addItem(new OrderItem(produto2, 2));

        VoucherDiscount cupom = new VoucherDiscount("PROMO20", 20.0, LocalDate.now().plusDays(5));
        order.addVoucher(cupom);

        System.out.println("Total do pedido: R$" + order.calculateTotal());
    }
}
