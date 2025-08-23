package com.techplus.ecommerce.discount;

import com.techplus.ecommerce.domain.model.*;
import com.techplus.ecommerce.domain.model.discount.DiscounVoucherStrategy;
import com.techplus.ecommerce.domain.model.discount.DiscountStrategy;
import com.techplus.ecommerce.domain.model.discount.DiscountVipStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class DiscountTest {

    static Customer clienteVip;
    static Customer clienteNaoVip;

    @BeforeAll
    public  static void setUp() {
        clienteVip = new Customer("USER_TESTE", true);
        clienteNaoVip = new Customer("USER_NAO_VIP_TEST", false);

    }


    @Test
    void shouldAddDiscountWithValidVoucher() {
        Order order = new Order(clienteNaoVip);
        Product product = new Product("PRODUTO_TEST", 100, true);

        order.addItem(new OrderItem(product, 1));
        VoucherDiscount voucher = new VoucherDiscount("TEST_CUPOM", 10, LocalDate.now().plusDays(1));
        order.addVoucher(voucher);

        DiscountStrategy discountStrategy = new DiscounVoucherStrategy();
        double discount = discountStrategy.calculateDiscount(order);

        assertEquals(discount, 10);
    }

    @Test
    void shouldAddVipDiscount() {
        Order order = new Order(clienteVip);
        Product product = new Product("PRODUTO_TEST", 200, true);

        order.addItem(new OrderItem(product, 1));


        DiscountStrategy discountStrategy = new DiscountVipStrategy();
        double discount = discountStrategy.calculateDiscount(order);

        assertEquals(discount, 10);
    }

    @Test
    void shouldNotBeAbleToAddDiscountVipToNotVipCustomer() {
        Order order = new Order(clienteNaoVip);
        Product product = new Product("PRODUTO_TEST", 200, true);

        order.addItem(new OrderItem(product, 1));


        DiscountStrategy discountStrategy = new DiscountVipStrategy();
        double discount = discountStrategy.calculateDiscount(order);

        assertEquals(discount, 0);
    }
}
