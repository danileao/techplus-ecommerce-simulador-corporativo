package com.techplus.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private CupomDesconto cupom;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        if (!item.getProduto().isEmEstoque()) {
            throw new IllegalArgumentException("Produto fora de estoque: " + item.getProduto().getNome());
        }
        itens.add(item);
    }

    public void aplicarCupom(CupomDesconto cupom) {
        if (cupom != null && cupom.isValido()) {
            this.cupom = cupom;
        }
    }

    public double calcularTotal() {
        double total = itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
        if (cupom != null) {
            total -= cupom.getValor();
        }
        if (cliente.isVip()) {
            total *= 0.95; // 5% de desconto para clientes VIP
        }
        return total;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
