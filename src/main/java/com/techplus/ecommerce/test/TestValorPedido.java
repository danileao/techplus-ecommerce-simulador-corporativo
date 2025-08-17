package com.techplus.ecommerce.test;

import com.techplus.ecommerce.*;
import com.techplus.ecommerce.desconto.DescontoCupomStrategy;
import com.techplus.ecommerce.desconto.DescontoVipStrategy;

import java.time.LocalDate;

public class TestValorPedido {

    // Payload para reproduzir
    public static void main(String[] args) {
        Cliente cliente = new Cliente("USER_TEST", false);
        Produto produto = new Produto("Livro", 100.00, true);

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(produto, 1));

//        CupomDesconto cupom = new CupomDesconto("PROMO10", 10.00, LocalDate.now().plusDays(3));
//        pedido.aplicarCupom(cupom);

        pedido.adicionarEstrategia(new DescontoVipStrategy());
        pedido.adicionarEstrategia(new DescontoCupomStrategy());

        System.out.println("Total calculado: R$" + pedido.calcularTotal());
    }
}
