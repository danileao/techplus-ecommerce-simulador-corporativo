package com.techplus.ecommerce.desconto;

import com.techplus.ecommerce.Pedido;

public class DescontoCupomStrategy implements  DescontoStrategy{
    @Override
    public double calcularDesconto(Pedido pedido) {
       if(pedido.getCupom() != null && pedido.getCupom().isValido()) {
           return pedido.getCupom().getValor();
        }

       return 0;
    }
}
