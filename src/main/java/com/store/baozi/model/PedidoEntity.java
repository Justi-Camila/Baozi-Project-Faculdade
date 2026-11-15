package com.store.baozi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @NotNull
    private Integer quantidade;

    public PedidoEntity() {}

    public PedidoEntity(Long id, ClienteEntity clienteId, ProdutoEntity produtoId, Integer quantidade) {
        this.id = id;
        this.cliente = clienteId;
        this.produto = produtoId;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public ProdutoEntity getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
