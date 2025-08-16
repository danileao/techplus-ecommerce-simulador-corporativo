package com.techplus.ecommerce;

public class Cliente {
    private String nome;
    private boolean vip;

    public Cliente(String nome, boolean vip) {
        this.nome = nome;
        this.vip = vip;
    }

    public boolean isVip() {
        return vip;
    }

    public String getNome() {
        return nome;
    }
}
