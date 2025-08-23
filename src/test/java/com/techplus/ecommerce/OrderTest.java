package com.techplus.ecommerce;

import static org.junit.jupiter.api.Assertions.*;

import com.techplus.ecommerce.domain.model.*;
import com.techplus.ecommerce.domain.model.discount.DiscountStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OrderTest {

     static Customer clienteVip;
    static Customer clienteNaoVip;

    @BeforeAll
    public  static void setUp() {
        clienteVip = new Customer("USER_TESTE", false);
        clienteNaoVip = new Customer("USER_NAO_VIP_TEST", true);

    }

    @Test
    public void shouldBeAbleToCalculateTotalWithoutDiscount() {

        Product product = new Product("PRODUTO_TESTE", 50, true);
        Order order = new Order(clienteNaoVip);
        order.addItem(new OrderItem(product, 2));

        double total = order.calculateTotal();

       assertEquals(100, total);
    }

    @Test
    public void shouldBeAbleToAddItemWithAvailableProduct() {
       Product product = new Product("PRODUTO_TEST", 100, true);
       OrderItem item = new OrderItem(product, 1);
       Order order = new Order(clienteNaoVip);

       order.addItem(item);
       assertEquals(order.getItems().size(), 1);
       assertEquals(order.getItems().get(0).getProduct().getName(), "PRODUTO_TEST");
    }

    @Test
    public void shouldValidateQuantityOrderItem() {
        Product product = new Product("PRODUTO_TEST", 100, true);
        OrderItem orderItem = new OrderItem(product,3);

        int quantidade=  orderItem.getQuantity();
        assertEquals(3, quantidade, "A quantidade retornada deve ser 3");
    }

    @Test
    public void shouldThrowExceptionWithUnavailableProduct() {
            Order order = new Order(clienteNaoVip);
            Product productForaDeEstoque = new Product("PRODUTO_FORA_ESTOQUE_TESTE", 200, false);
            OrderItem item = new OrderItem(productForaDeEstoque,1);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                order.addItem(item);
            });

            assertTrue(exception.getMessage().contains("Produto fora de estoque:"));
            assertEquals(exception.getMessage(), "Produto fora de estoque: " + item.getProduct().getName());
    }

    @Test
    void shouldAddValidVoucher() {
        Order order = new Order(clienteVip);

        VoucherDiscount cupom = new VoucherDiscount("TESTE_CUPOM", 10, LocalDate.now().plusDays(1));
        order.addVoucher(cupom);

        assertEquals(cupom, order.getVoucherDiscount());
    }


    @Test
    void shouldNotBeAbleToAddInvalidVoucher() {
        Order order = new Order(clienteVip);
        VoucherDiscount cupom = new VoucherDiscount("TESTE_CUPOM_EXPIRADO", 10, LocalDate.now());
        order.addVoucher(cupom);

        assertNull(order.getVoucherDiscount());
    }

    @Test
    void shouldBeAbleAddDiscountStrategy() { //Functional Interface
        Order order = new Order(clienteVip);

        Product product = new Product("PRODUTO_TESTE", 100, true);
        order.addItem(new OrderItem(product, 1));

        DiscountStrategy strategyMock = p -> 10;
        order.addStrategy(strategyMock);

        double total = order.calculateTotal();
        assertEquals(total, 90);


    }
}
