package com.techplus.ecommerce;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Dani", true);
        Produto produto1 = new Produto("Notebook", 3500.00, true);
        Produto produto2 = new Produto("Mouse", 150.00, true);

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(produto1, 1));
        pedido.adicionarItem(new ItemPedido(produto2, 2));

        CupomDesconto cupom = new CupomDesconto("PROMO20", 20.0, LocalDate.now().plusDays(5));
        pedido.aplicarCupom(cupom);

        System.out.println("Total do pedido: R$" + pedido.calcularTotal());
    }
}
