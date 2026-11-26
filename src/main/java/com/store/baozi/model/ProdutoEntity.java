package com.store.baozi.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private BigDecimal preco;

    private Boolean estoque;

    public ProdutoEntity(String nome, BigDecimal preco, Boolean estoque) {
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Boolean getEstoque() {
        return estoque;
    }
}
