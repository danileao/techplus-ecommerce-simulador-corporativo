package com.techplus.ecommerce.adapter.in.console;

import com.techplus.ecommerce.adapter.out.ProductRepositoryInMemory;
import com.techplus.ecommerce.application.service.OrderService;
import com.techplus.ecommerce.domain.model.discount.DiscountVipStrategy;
import com.techplus.ecommerce.domain.model.Customer;
import com.techplus.ecommerce.domain.model.Order;
import com.techplus.ecommerce.domain.model.Product;
import com.techplus.ecommerce.domain.model.VoucherDiscount;

import java.time.LocalDate;

public class EcommerceConsoleApp {

    public static void main(String[] args) {
        var productRepo = new ProductRepositoryInMemory();
        var orderService = new OrderService(productRepo);

        Customer customer = new Customer("Dani", true);
        Order order = new Order(customer);
        productRepo.save(new Product("Produto de Notebook", 3500, true));
        System.out.println("----------");
        productRepo.save(new Product("Mouse", 150, true));


        orderService.addItem(order, "Produto de Notebook", 1);
        orderService.addItem(order, "Mouse", 2);

        VoucherDiscount voucher = new VoucherDiscount("PROMO20", 20, LocalDate.now().plusDays(5));
        orderService.addVoucher(order, voucher);
//        order.addStrategy(new DiscounVoucherStrategy());
        order.addStrategy(new DiscountVipStrategy());

        double total = orderService.calculateTotal(order);
        System.out.println("O Total do pedido: R$ " + total);
    }
}
