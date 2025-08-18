package com.techplus.ecommerce.desconto;

import com.techplus.ecommerce.ItemPedido;
import com.techplus.ecommerce.Pedido;

public class DescontoVipStrategy implements  DescontoStrategy{
    @Override
    public double calcularDesconto(Pedido pedido) {
       if(pedido.getCliente().isVip()) {
           double subtotal = pedido.getSubtotal();
           return subtotal * 0.05;
       }
       return  0;
    }
}
