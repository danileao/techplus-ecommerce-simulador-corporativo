package com.techplus.ecommerce.desconto;

import com.techplus.ecommerce.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class DescontoTeste {

    static Cliente clienteVip;
    static Cliente clienteNaoVip;

    @BeforeAll
    public  static void setUp() {
        clienteVip = new Cliente("USER_TESTE", true);
        clienteNaoVip = new Cliente("USER_NAO_VIP_TEST", false);

    }


    @Test
    void deveAplicarDescontoDoCupomValido() {
        Pedido pedido = new Pedido(clienteNaoVip);
        Produto produto = new Produto("PRODUTO_TEST", 100, true);

        pedido.adicionarItem(new ItemPedido(produto, 1));
        CupomDesconto cupom = new CupomDesconto("TEST_CUPOM", 10, LocalDate.now().plusDays(1));
        pedido.aplicarCupom(cupom);

        DescontoStrategy descontoStrategy = new DescontoCupomStrategy();
        double desconto = descontoStrategy.calcularDesconto(pedido);

        assertEquals(desconto, 10);
    }

    @Test
    void deveAplicarDescontoVipDeCincoPorCento() {
        Pedido pedido = new Pedido(clienteVip);
        Produto produto = new Produto("PRODUTO_TEST", 200, true);

        pedido.adicionarItem(new ItemPedido(produto, 1));


        DescontoStrategy descontoStrategy = new DescontoVipStrategy();
        double desconto = descontoStrategy.calcularDesconto(pedido);

        assertEquals(desconto, 10);
    }

    @Test
    void naoDeveSerPossivelAplicarDescontoVipParaClienteNaoVip() {
        Pedido pedido = new Pedido(clienteNaoVip);
        Produto produto = new Produto("PRODUTO_TEST", 200, true);

        pedido.adicionarItem(new ItemPedido(produto, 1));


        DescontoStrategy descontoStrategy = new DescontoVipStrategy();
        double desconto = descontoStrategy.calcularDesconto(pedido);

        assertEquals(desconto, 0);
    }
}
