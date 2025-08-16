package com.techplus.ecommerce;

import java.time.LocalDate;

public class CupomDesconto {
    private String codigo;
    private double valor;
    private LocalDate validade;

    public CupomDesconto(String codigo, double valor, LocalDate validade) {
        this.codigo = codigo;
        this.valor = valor;
        this.validade = validade;
    }

    public boolean isValido() {
        return LocalDate.now().isBefore(validade);
    }

    public double getValor() {
        return valor;
    }

    public String getCodigo() {
        return codigo;
    }
}
