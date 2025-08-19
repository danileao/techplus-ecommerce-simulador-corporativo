package com.techplus.ecommerce;

import static org.junit.jupiter.api.Assertions.*;

import com.techplus.ecommerce.desconto.DescontoStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PedidoTest {

     static  Cliente clienteVip;
    static Cliente clienteNaoVip;

    @BeforeAll
    public  static void setUp() {
        clienteVip = new Cliente("USER_TESTE", false);
        clienteNaoVip = new Cliente("USER_NAO_VIP_TEST", true);

    }

    @Test
    public void deveCalcularTotalSemDescontos() {

        Produto produto = new Produto("PRODUTO_TESTE", 50, true);
        Pedido pedido = new Pedido(clienteNaoVip);
        pedido.adicionarItem(new ItemPedido(produto, 2));

        double total = pedido.calcularTotal();

       assertEquals(100, total);
    }

    @Test
    public void deveAdicionarItemQuandoProdutoEmEstoque() {
       Produto produto = new Produto("PRODUTO_TEST", 100, true);
       ItemPedido item = new ItemPedido(produto, 1);
       Pedido pedido = new Pedido(clienteNaoVip);

       pedido.adicionarItem(item);
       assertEquals(pedido.getItens().size(), 1);
       assertEquals(pedido.getItens().get(0).getProduto().getNome(), "PRODUTO_TEST");
    }

    @Test
    public void deveValidarQuantidadeDoPedido() {
        Produto produto = new Produto("PRODUTO_TEST", 100, true);
        ItemPedido itemPedido = new ItemPedido(produto,3);

        int quantidade=  itemPedido.getQuantidade();
        assertEquals(3, quantidade, "A quantidade retornada deve ser 3");
    }

    @Test
    public void deveLancarExcecaoProdutoForaDeEstoque() {
            Pedido pedido = new Pedido(clienteNaoVip);
            Produto produtoForaDeEstoque = new Produto("PRODUTO_FORA_ESTOQUE_TESTE", 200, false);
            ItemPedido item = new ItemPedido(produtoForaDeEstoque,1);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
                pedido.adicionarItem(item);
            });

            assertTrue(exception.getMessage().contains("Produto fora de estoque:"));
            assertEquals(exception.getMessage(), "Produto fora de estoque: " + item.getProduto().getNome());
    }

    @Test
    void deveAplicarCupomValido() {
        Pedido pedido = new Pedido(clienteVip);

        CupomDesconto cupom = new CupomDesconto("TESTE_CUPOM", 10, LocalDate.now().plusDays(1));
        pedido.aplicarCupom(cupom);

        assertEquals(cupom, pedido.getCupom());
    }


    @Test
    void naoDeveAplicarCupomInvalido() {
        Pedido pedido = new Pedido(clienteVip);
        CupomDesconto cupom = new CupomDesconto("TESTE_CUPOM_EXPIRADO", 10, LocalDate.now());
        pedido.aplicarCupom(cupom);

        assertNull(pedido.getCupom());
    }

    @Test
    void deveAdicionarEstrategiaDeDesconto() { //Functional Interface
        Pedido pedido = new Pedido(clienteVip);

        Produto produto = new Produto("PRODUTO_TESTE", 100, true);
        pedido.adicionarItem(new ItemPedido(produto, 1));

        DescontoStrategy strategyMock = p -> 10;
        pedido.adicionarEstrategia(strategyMock);

        double total = pedido.calcularTotal();
        assertEquals(total, 90);


    }
}
