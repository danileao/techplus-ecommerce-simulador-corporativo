package com.techplus.ecommerce;

public class Produto {
    private String nome;
    private double preco;
    private boolean emEstoque;

    public Produto(String nome, double preco, boolean emEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.emEstoque = emEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public boolean isEmEstoque() {
        return emEstoque;
    }

    public String getNome() {
        return nome;
    }
}
