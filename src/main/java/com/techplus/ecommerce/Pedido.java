package com.techplus.ecommerce;

import com.techplus.ecommerce.desconto.DescontoStrategy;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private CupomDesconto cupom;
    List<DescontoStrategy> estrategias = new ArrayList<>();

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

    public void adicionarEstrategia(DescontoStrategy estrategia) {
        estrategias.add(estrategia);
    }

    public double getSubtotal() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }

    public double calcularTotal() {
       double subtotal = getSubtotal();

       double totalDescontos = estrategias.stream().mapToDouble(e -> e.calcularDesconto(this)).sum();

       return subtotal - totalDescontos;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }


    public CupomDesconto getCupom() {
        return cupom;
    }
}
