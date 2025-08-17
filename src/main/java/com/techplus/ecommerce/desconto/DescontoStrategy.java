package com.techplus.ecommerce.desconto;

import com.techplus.ecommerce.Pedido;

public interface DescontoStrategy {

    double calcularDesconto(Pedido pedido);
}
